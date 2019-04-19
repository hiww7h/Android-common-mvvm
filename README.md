# Android MVVM 框架搭建

## MVVM 概述

### Model–View–ViewModel (MVVM) 是一个软件架构设计模式

MVC：（View-Model-Controller）
将View、Model、Controller代码块进行划分，使得程序大部分分离，降低耦合。

MVP：（VIew-Model-Presenter）
![Image text](https://raw.githubusercontent.com/ww7hcom/StaticFiles/master/mvvmimagefile/MVP.png)
由于MVC中View和Model之间的依赖太强，导致Activity中的代码过于臃肿。为了他们可以绝对独立的存在，慢慢演化出了MVP。在MVP中View并不直接使用Model，它们之间的通信是通过 Presenter (MVC中的Controller)来进行的。


MVVM：（Model–View–ViewModel）
![Image text](https://raw.githubusercontent.com/ww7hcom/StaticFiles/master/mvvmimagefile/MVVM.png)
MVVM可以算是MVP的升级版，将Presenter改名为ViewModel。关键在于View和Model的双向绑定，当View有用户输入后，ViewModel通知Model更新数据，同理Model数据更新后，ViewModel通知View更新。

### MVVM 使用到的库

1、[Android官方架构组件Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
2、Android官方架构组件ViewModel
3、Android官方架构组件LiveData
4、Android官方架构组件Paging
5、Android官方架构组件Navigation
6、Android官方架构组件Data

## MVVM 实现



