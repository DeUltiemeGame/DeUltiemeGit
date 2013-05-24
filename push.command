cd /Android\ SDK/workspace/DeUltiemeGit

read -p "Commit comment?" comment

git add .
read -p "d"
git commit -am $comment
read -p "d"
git push
read -p "d"