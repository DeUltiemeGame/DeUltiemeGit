cd /Android\ SDK/workspace/DeUltiemeGit

read -p "Commit comment?" comment

git add .

git commit -am "$comment"

git push
