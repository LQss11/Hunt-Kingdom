To use the logging bundle install 
first change in php.ini set mem limit to -1

    composer require monolog/monolog
    composer require symfony/http-client

update this in composer json if version incompatible
"platform": {
            "php": "7.1"
        }



add monolog config

monolog:
    handlers:
        es:
            type: service
            id: Symfony\Bridge\Monolog\Handler\ElasticsearchLogstashHandler

add service config 

services:
    Symfony\Bridge\Monolog\Handler\ElasticsearchLogstashHandler:
        autowire: false

Install docker CLI
then run these commands in terminal:

docker network create sf-es-k
docker run -it --rm --name elasticsearch -p 9200:9200 -e "discovery.type=single-node" --network sf-es-k docker.elastic.co/elasticsearch/elasticsearch:7.4.2
docker run -it --rm -p 5601:5601 --network sf-es-k docker.elastic.co/kibana/kibana:7.4.2

Create a file for elasticsearch class handler in vendor/symfony/symfony/bridge/handler
    https://github.com/symfony/monolog-bridge/blob/master/Handler/ElasticsearchLogstashHandler.php

followed this
https://jolicode.com/blog/how-to-visualize-symfony-logs-in-dev-with-elasticsearch-and-kibana