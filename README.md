# maze分支
1、在MazeGenerator中，生成一个和当前屏幕大小相同（形状为正方形，边长为屏幕长宽中较小的一个）的二维数组。接着，利用MazeGenerator中的generateMaze函数将此二维数组变为一个01组成的迷宫。  

2、在World中，利用MazeGenerator中生成的二维数组，在titles中布置相应的Wall，Floor已经Target。最后，在(0,0)的位置放置葫芦娃。

3、在Calabash类中，新增tryMove函数，让葫芦娃尝试向上下左右四个方向移动：如果遇到Wall则无法移动，否则移动到指定位置，并将原先的位置的tile上的thing变为Track，视作已经移动过的位置。

4、在DFS类中，实现了一个基于二维数组的dfs寻路方法。此类对象可以用Tile[][]来进行初始化，在完成dfs计算后会将移动结果储存在一个ArrayList\<Node\>类型的对象plan中，并可以通过getPlan函数得到此plan。

5、在WordlScreen中的respondToUserInput函数中，处理按键输入：若按键为上下左右，则让葫芦娃利用tryMove函数进行移动；若按下回车键，则根据DFS的结果控制葫芦娃移动。