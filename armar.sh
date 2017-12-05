echo "Borrar archivos"
rm -rf src/main/webapp/*
echo "Copiar Version"
cp -a ../go-ng-admin/dist/. src/main/webapp
echo "Maven"
mvn clean install
