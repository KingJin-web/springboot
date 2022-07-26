#! /bin/bash
# shellcheck disable=SC2164
cd H:/jetbrains/java/springboot
echo "----------本地库状态----------"
git status
echo "----------本地库状态----------"
while :
do
	read -r -p "是否继续提交? [Y/N] " input
	case $input in
		[yY][eE][sS]|[yY])
			echo "----------继续提交----------"
			createAt=$(date "+%Y-%m-%d %H:%M:%S")
			git add .
			git commit -m "${createAt}"
			git push -u origin "master"
			echo "----------提交成功----------"
			;;
		[nN][oO]|[nN])
			echo "----------中断提交----------"
			sleep 1
			exit 1
				;;
		*)
		echo "输入错误，请重新输入"
		continue
		;;
	esac
done
