# Git原理及使用方法
版本控制 主动提交 中央仓库 构成版本控制VCS核心

中央式版本控制系统

1.创建一个中央仓库

2.团队每个人开发独立的功能再上传到中央仓库

分布式vcs

与中央式的核心区别是分布式会增加一个本地仓库，查看历史提交代码可以在本地完成，将保留历史版本的任务留存在本地，中央代码仓库作为团队间的同步中转站，就是将中央式的提交和上传拆开了

1.创建中央仓库

2.其他人克隆到本地，可以不断将开发的功能提交到本地仓库，直到开发成完整独立的功能，再上传到中央仓库

3.其他人可以将这些提交的代码同步到自己的机器上与自己本地代码合并

### Git基本操作
git status :用来查看工作目录当前状态

git add 文件名/ . :让git开始跟踪，它将文件添加到staging area（暂存区，.git的index文件中），暂存区的文件才可以commit

git commit ：将代码提交到本地仓库

git log ：列出提交历史

git push ：把本地提交上传到中央 

git push --set-upstream origin 分支名：将新的分支上传到远程仓库,与远程分支建立联系

git push origin 枝干名 ：上传远端仓库

git branch  名称：创建一个枝干

git branch -d 分支名 ：删除分支，1.head所指向的分支不能被删除，branch只是一个引用 2.删除这个branch不会删除任何commit
 3.没有被合并到master上的branch不能被删除 删除之后别的同事依然可以看到分支需要执行git remote prune origin

git checkout 分支名 ：切换到某一个分支 下载下来一个分支

git checkout -b 分支名 ：创建并切换分支

git reflog :打印所有引用的操作

git remote prune origin : 删除远程分支不存在的分支

git merge 分支名 ：合并分支

git rebase 分支名 ：合并分支但是没有历史分叉，他会把其他分支的commit按照顺序在提交到分支名对应的分支，默认checkout的分支，消除分支

git rebase -i HARD^^/~~ ：

git reset --hard HEAD^ :往回退1个  ^的个数指退几个 ~是前进几个
 


###Git原理
Head 永远指向当前工作目录所对应的最新一次commit，一个引用，远程仓库的head永远指向master分支

master 主干，一个引用

branch 一个commit所指向的内容，所有branch都是平等的，一个引用

push本质把当前branch的位置上传到远端仓库，并把它路径上的commit一并上传，git push不加origin 分支参数时只能上传之前从远端clone下来或者pull下来的
内容，如果本地新建的分支需要上传到指定仓库需要加远程仓库名

merge从目标commit和当前commit分叉的位置起，把目标commit路径上的所有commit一并应用到当前commit，生成一个新的commit，merge操作会帮你自动简要
填写提交的信息，merge完成后是完成commit的，检查无误后可以直接push

冲突A和B同时更改了同一行，会导致冲突发生

###Git 常见问题

创建一个分支

git checkout -b 分支名 ：创建并切换分支

git push --set-upstream origin 分支名

删除git管理的文件，并将这次删除放入暂存区

git rm 【filename】

停止追踪指定文件，但该文件会保留在工作区

git rm -cached【file】

若是idea编辑器需要将需要不追踪的文件添加进入.gitignore
,在使用git rm -cacth -r filename,将git add .gitignore
git commit -m"gitignore提交删除idea"，git push origin master

冲突 当两个分支都修改了同一个文件的同一行代码就会出现冲突

解决冲突

head为当前commit的内容，下方会有merge branch的内容，手动决定保留哪个，解决完成后执行commit

冲突的两种情况

1：head领先于commit的内容，什么都不做

2：merge 一个领先于当前head的分支会同步到最新的commit

完整的一次分支开发

git checkout -b 分支名

git push --set-upstream origin 分支名

同事操作

git pull 

git checkout 分支名 ：进行确认看看有没有问题若无意见

git checkout master

git pull ：merge 之前一定pull让master保证最新

git merge 分支名

git push

git branch -d 分支名 ：删除本地仓库分支

git push origin -d 分支名

如有意见checkout后去修改，直到无意间在执行剩下

rebase操作

git checkout 分支名

git rebase master 将分支切换点换成分支最新的commit，在其后面添加master与分支分开后面的节点，消除分支，但是master还指向之前的commit，需要保证提交顺序的一致

git checkout mater

git merge 分支名 ：这个操作可以避免丢失commit


修改commit中错误的内容

git add 修改后的内容

git commit --amend git会再刚提交的错误commit中加上新的内容形成一个commit





最佳练习









