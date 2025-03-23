* to execute and run test cases

  mvn clean install exec:java -Dexec.mainClass="mainapp.MyApp" -DskipTests=true

git config --global user.email ""
git config --global user.name ""
================================================================================
echo "Initial content" > file.txt
git add file.txt
git commit -m "Initial commit"

echo "Feature work in progress" >> file.txt
git add file.txt

git stash save "Work in progress - feature implementation"

echo "Critical bug fix" > bugfix.txt
git add bugfix.txt
git commit -m "Fix critical bug"

git stash branch feature-branch

git checkout main