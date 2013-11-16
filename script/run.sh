
set -e
cd "$(dirname $0)/.."

mvn compile

classpath="target/classes"
for i in $(find lib -iname \*.jar); do
	classpath=$classpath:$i
done

java -cp $classpath it.xpug.scopa.main.Main
