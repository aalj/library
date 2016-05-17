# 图书馆
完成页面框架的搭建，
采用基本的mvp模式进行开发

主要分为四大模块

1. 首页主要的功能有，顶部广告位展示，馆藏查询，续借，二维码，活动，讲座，推荐，电子资源，等几大更能的入口
2. 快速检索模块，主要用于输入任意内容快速检索到对应的资源
3. 个人中心，管理个人的一些内容
4. 其他模块 ，展示提供的活动，新闻之内的

4/28/2016 8:26:20 PM


首页页面的实现，但是图片轮播需要实现，之前使用的存在问题需要重写

1. 使用toolbar实现首页标题栏的文字改变，但是由于考虑到界面显示问题，在实际使用过程中并没有改变文字
2. 个人中心，展示出来的功能
3. 颜色 66d2fb  e6e6e6
 
 * 已借图书，
 * 图书续借，
 * 图书荐购
 * 借阅历史
 * 报名讲座
 * 收藏  
 * 缓存清理
 * 离线下载
 * 关于我们





ScrollView.fillViewport=true的作用






Recyclerview的上拉加载
recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()  返回最后一个加载的试图

onScrolled(RecyclerView recyclerView, int dx, int dy) 这个方法会实时的调用，同时传入的参数是移动距离

onScrollStateChanged(RecyclerView recyclerView, int newState) 该方法是在recyclerview滑动状态改变是调用，分别有三个状态
1. SCROLL_STATE_IDLE   当前recyclerview没有滚动
2. SCROLL_STATE_SETTLING   RecyclerView目前滑动到最后一个位置,而不是外部控制。
3. TOUCH_SLOP_DEFAULT  


通过使用 SCROLL_STATE_IDLE 状态和 findLastCompletelyVisibleItemPosition() 的到当前的最后一个位置，然后与recyclerview总个数-1（recyclerView.getAdapter().getItemCount() - 1）进行比较，判断当前是否到最后，然后进行加载更多的操作





