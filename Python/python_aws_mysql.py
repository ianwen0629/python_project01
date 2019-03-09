from __future__ import print_function
import mysql.connector
from mysql.connector import errorcode
import ast

cnx = mysql.connector.connect(user='root', database='python')
cursor = cnx.cursor()

db_name = 'python'
cnx.database = db_name

TABLES = {}
TABLES['aws_pricing_book'] = (
    "CREATE TABLE `aws_pricing_book` ("
    "  `id` int(11) NOT NULL AUTO_INCREMENT,"
    "  `sku` varchar(20) NOT NULL,"
    "  `rate_code` varchar(64) NOT NULL,"
    "  `term_type` varchar(20) NOT NULL,"
    "  `product_family` varchar(30) NOT NULL,"
    "  `service_code` varchar(20) NOT NULL,"
    "  `location` varchar(30) NOT NULL,"
    "  `instance_type` varchar(20) NOT NULL,"
    "  `tenancy` varchar(20) NOT NULL,"
    "  `operating_system` varchar(20) NOT NULL,"
    "  `license_model` varchar(30) NOT NULL,"
    "  `usage_type` varchar(255) DEFAULT NULL,"
    "  `price_per_unit` decimal(20,10) NOT NULL,"
    "  `description` varchar(128) NOT NULL,"
    "  `upd_time` varchar(20) DEFAULT NULL COMMENT 'yyyy/mm/dd hh:mi:ss',"
    "  PRIMARY KEY (`id`),"
    "  UNIQUE KEY `rate_code` (`rate_code`)"
    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;")

'''
def create_database(cursor):
    try:
        cursor.execute(
            "CREATE DATABASE {} DEFAULT CHARACTER SET 'utf8'".format(db_name))
    except mysql.connector.Error as err:
        print("Failed creating database: {}".format(err))
        exit(1)

try:
    cnx.database = db_name  
except mysql.connector.Error as err:
create_database(cursor)
'''

for name, ddl in TABLES.items():
    try:
        print("Creating table {}: ".format(name), end='')
        cursor.execute(ddl)
    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
            print("already exists.")
        else:
            print(err.msg)
    else:
        print("OK")

add_price = (
    "INSERT INTO aws_pricing_book ("
    "  id,"
    "  sku,"
    "  rate_code,"
    "  term_type,"
    "  product_family,"
    "  service_code,"
    "  location,"
    "  instance_type,"
    "  tenancy,"
    "  operating_system,"
    "  license_model,"
    "  usage_type,"
    "  price_per_unit,"
    "  description,"
    "  upd_time"
    ") VALUES ("
    "  %(id)s,"
    "  %(sku)s,"
    "  %(rate_code)s,"
    "  %(term_type)s,"
    "  %(product_family)s,"
    "  %(service_code)s,"
    "  %(location)s,"
    "  %(instance_type)s,"
    "  %(tenancy)s,"
    "  %(operating_system)s,"
    "  %(license_model)s,"
    "  %(usage_type)s,"
    "  %(price_per_unit)s,"
    "  %(description)s,"
    "  %(upd_time)s)")

file = open(r'price.json')
instance_data = ''
data_id = 1

for row in file:
    write = True

    if '"publicationDate" : ' in row:
        time = row.replace('  "publicationDate" : "','')
        time = time.replace('T', ' ')
        time = time.replace('Z"', '')
        time = time.replace(',','')
        print(time)
    
    if '"sku" : ' in row:
        instance_data = row
        for index in row:
            line = next(file)
            if '"servicename" : ' in line:
                break
            else:
                instance_data = instance_data + line
    
    elif '"servicename" : ' in row:
        temp = instance_data + line
        temp = '{' + temp + '}}'
        instance_data = ast.literal_eval(temp)
        for key in instance_data:
            attributes = pk['attributes']
            
            if 'sku' in instance_data:
                sku = instance_data['sku']
            if 'productFamily' in instance_data:
                product_family = instance_data['productFamily']
            if 'servicecode' in attributes:
                servicecode = attributes['servicecode']
            if 'location' in attributes:
                location = attributes['location']
            if 'instanceType' in attributes:
                instance_type = attributes['instanceType']
            else:
                write = False
            if 'tenancy' in attributes:
                tenancy = attributes['tenancy']
            if 'operatingSystem' in attributes:
                operating_system = attributes['operatingSystem']
            if 'licenseModel' in attributes:
                license_model = attributes['licenseModel']
            if 'usagetype' in attributes:
                usagetype = attributes['usagetype']
        
        second_file = open(r'price.json')
        for row in second_file:
            next(second_file)
            if '"terms" : {' in row:
                break
        
        for row in second_file:
            rate_code = '"rateCode" : '
            if rate_code in row:
                if sku in row:
                    instance_price = row.replace('  ', '')
                    for index in row:
                        line = next(second_file)
                        instance_price = instance_price+line.replace('  ', '')
                        if '"appliesTo" : [' in line:
                            break
                    
                    temp = instance_price.replace('\n', '')
                    temp = temp+'}'
                    instance_price = ast.literal_eval('{' + temp)
                    price_per_unit = instance_price['pricePerUnit']
                    
                    if ('rateCode' in instance_price):
                        rate_code = instance_price['rateCode']
                    if ('pricePerUnit' in instance_price):
                        price_per_unit = float(price_per_unit['USD'])
                    if ('description' in instance_price):
                        description = instance_price['description']
                    
                    if tenancy == 'Reserved':
                        term_type = 'Reserved'
                    else:
                        term_type = 'OnDemand'
                    
                    if write == False:
                        print('*skipped', exr['sku'], 'because productFamily =', exr['productFamily'])
                        break
                    
                    data = {
                        'id': data_id ,
                        'sku': sku ,
                        'rate_code': rate_code ,
                        'term_type': term_type ,
                        'product_family': product_family ,
                        'service_code': service_code ,
                        'location': location ,
                        'instance_type': instance_type ,
                        'tenancy': tenancy ,
                        'operating_system': operating_system ,
                        'license_model': license_model ,
                        'usage_type': usage_type ,
                        'price_per_unit': price_per_unit ,
                        'description': description ,
                        'upd_time': time,
                    }
                    data_id = data_id+1
                    break
        
        second_file.close()
        if write == True:
            cursor.execute(add_price, data)
            print('added', data_id-1)
    
    if '"terms" : {' in row:
        break

file.close()

print("*finished*")
cnx.commit()
cursor.close()
cnx.close()
