

# 搜索框智能提示接口

**简要描述：** 

- 搜索框智能提示接口

**请求URL：** 
- ` http://xx.com/search/complete

**请求方式：**

- GET 

**参数：** 

| 参数名   | 必选 | 类型    | 说明                        |
| :------- | :--- | :------ | --------------------------- |
| biz_type | 是   | string  | 搜索类型：service/shop/clue |
|          | 是   | string  | 用户输入关键词              |
| num      | 是   | integer | 最多返回结果条数            |

 **返回示例**

``` 
  {
    "code": 200,
    "message": "SUCCESS",
    "biz_type": "service_complete",
    "data": {
        "total": 2,
        "text" : "抖音",
        "data":[
            {
                "text" : "抖音音乐人",
                "_score" : 10.0
            },
            {
                "text" : "抖音音乐达人",
                "_score" : 8.0
            }
        ]
    }
  }
```

 **返回参数说明** 

| 参数名 | 类型    | 说明                         |
| :----- | :------ | ---------------------------- |
|        | integer | 返回状态码<br />200 正常返回 |

 **备注** 

- 更多返回错误代码请看首页的错误代码描述



# 服务列表接口


**简要描述：** 

- 服务列表接口

  包含关键词搜索、条件筛选、综合排序及条件排序

**请求URL：** 
- ` http://xx.com/search/service_search `

**请求方式：**

- GET 

**参数：**

| 参数名              | 必填 | 类型   | 说明                                                         |
| :------------------ | :--- | :----- | ------------------------------------------------------------ |
| uid                 | 是   | string | 用户uid                                                      |
| params.cate_code    | 否   | string | 分类code                                                     |
| params.page         | 是   | int    | 分页参数，url后面携带（从1开始计数）                         |
| params.**per_page** | 是   | int    | 分页参数，每页返回结果数量                                   |
| params.brand_code   | 否   | string | 品牌code                                                     |
| params.price_id     | 否   | int    | 价格id，价格筛选使用                                         |
| params.priceMin     | 否   | int    | 自定义筛选价格区间第一个值                                   |
| params.priceMax     | 否   | int    | 自定义筛选价格区间第二个值                                   |
| params.realm_id     | 否   | int    | 热门领域id，领域筛选使用                                     |
| params.region_id    | 否   | int    | 地区id，地区筛选使用                                         |
| params.filterArr    | 否   | array  | 其他规格属性筛选 <br />[{‘id’:1,’filter_Info’:’天’},{‘id’:27,’filter_Info’:’10次/天’}] |
| params.keywords     | 否   | string | 搜索关键字                                                   |
| params.order        | 是   | string | 排序条件<br />default 综合排序<br />sales  成交量排序<br />comments  好评率排序<br />price  价格排序<br />update  更新排序<br /> |
| params.sort         | 是   | string | 排序方式 ，（desc,asc）                                      |

**请求示例**

```json
{
      "uid": "123",
      "params": {
        "cate_code":"wangluo",
        "page":1,
          "per_page": 10,
        "brand_id":199,
        "price_id":"",
        "realm_id":1,
        "region_id":110100,
        "order":"default",
        "sort":"",
        "priceMin":"1000",
        "priceMax":"54321",
        "filterArr":[
            {
                "id":111,
                "filter_Info":"50000-100000",
                "name":"月活用户数"
            }
        ]
    }
}
```

####  服务列表接口的几种不同请求类型

```
# 搜索请求
{
      "uid": "123",
      "params": {
        "cate_code":"wangluo",
        "page":1,
          "per_page": 10,
        "brand_id":199,
        "price_id":"",
        "realm_id":1,
        "region_id":110100,
        "order":"default",
        "sort":"",
        "priceMin":"1000",
        "priceMax":"54321",
        "filterArr":[
            {
                "id":111,
                "filter_Info":"50000-100000",
                "name":"月活用户数"
            }
        ]
    }
}

# 待筛选的搜索


# 列表请求
```



**返回参数说明** 

| 参数名            | 类型    | 说明                                                         |
| :---------------- | :------ | ------------------------------------------------------------ |
| code              | integer | 返回状态码<br />200 正常返回                                 |
| message           | string  | 返回信息                                                     |
| biz_type          | string  | 业务类型：<br />service_list<br />service_filtered_list<br />service_search<br />service_filtered_search |
| data              | json    | 返回数据                                                     |
| data.total        | integer | 请求结果总条数                                               |
| data.current_page | integer | 请求结果分页当前页数（计数从1开始）                          |
| data.last_page    |         | 请求结果分页最后一页页数（计数从1开始）                      |
| data.per_page     | integer | 分页每页显示数量                                             |
| data.from         | integer | 当前分页结果起始元素在结果列表中的位置（计数从1开始）        |
| data.to           |         | 当前分页结果结束元素在结果列表中的位置（计数从1开始）        |
| data.page_size    | integer | 当前分页结果数量                                             |
| data.data         | list    | 当前分页结果列表，列表元素包含字段:<br />id：   item_id<br />type： item类型 |
| rec_data          |         | 搜索结果为空时的推荐数据（仅当用户通过关键词搜索结果为空时有数据） |
| rec_data.total    | integer | 搜索无结果页推荐数据个数                                     |
| rec_data.data     |         | 搜索无结果页推荐列表，列表元素包含字段:<br />id：   item_id<br />type： item类型 |



**返回示例1**

``` 
  {
    "code": 200,
    "message": "SUCCESS",
    "biz_type":"service_search",
    "data": {
        "total": 108,
        "current_page": 1,
        "last_page":11,
        "per_page": 10,
        "from": 1,
        "to": 10,
        "page_size": 10,
        "data":[
            {
                "id" : 73,
                "type" : "service"
            },
            {
                "id" : 60,
                "type" : "service"
            },
            {
                "id" : 132,
                "type" : "service"
            },
            {
                "id" : 113,
                "type" : "service"
            },
            {
                "id" : 89,
                "type" : "service"
            },
            {
                "id" : 64,
                "type" : "service"
            },
            {
                "id" : 65,
                "type" : "service"
            },
            {
                "id" : 66,
                "type" : "service"
            },
            {
                "id" : 67,
                "type" : "service"
            },
            {
                "id" : 68,
                "type" : "service"
            }
        ]
    }
  }
```

**返回示例2**

```
{
    "code": 200,
    "message": "SUCCESS",
    "biz_type":"service_search",
    "data": {
        "total": 0
    },
    "rec_data": {
        "total": 5,
        "data":[
            {
                "id" : 73,
                "type" : "service"
            },
            {
                "id" : 60,
                "type" : "service"
            },
            {
                "id" : 132,
                "type" : "service"
            },
            {
                "id" : 113,
                "type" : "service"
            },
            {
                "id" : 89,
                "type" : "service"
            }
        ]
    }
  }
```



 **备注** 

- 更多返回错误代码请看首页的错误代码描述



# 店铺列表接口

**简要描述： **

- 店铺列表接口

  包含关键词搜索、条件筛选、综合排序及条件排序

**请求URL：** 

- ` http://xx.com/search/store_search `

**请求方式：**

- GET 

**请求参数说明：**

| 参数名              | 必选 | 类型   | 说明                                                         |
| :------------------ | :--- | :----- | ------------------------------------------------------------ |
| uid                 | 是   | string | 用户uid                                                      |
| params.page         | 否   | int    | 分页参数，url后面携带（从1开始计数）                         |
| **params.per_page** | 否   | int    | 分页参数，每页返回结果数量                                   |
| params.cat_id       | 否   | string | 筛选分类id(一级类目)                                         |
| params.region_id    | 否   | string | 筛选地区id                                                   |
| params.order        | 否   | string | 排序条件：<br />default  综合排序<br />sales  成交量排序<br />comments  好评率排序<br />time  开通时间排序 |
| params.sort         | 否   | string | 排序方式（’asc’,’desc’）                                     |
| params.keywords     | 否   | string | 搜索关键字                                                   |

**请求示例**

```json
{
    "uid": "123",
    "params": {
        "page":1,
        "per_page": 10,
        "cat_id":2,
        "region_id":"",
        "order":"sales",
        "sort":"asc",
        "keywords":""
    }
}
```



**返回参数说明** 

| 参数名            | 类型    | 说明                                                         |
| :---------------- | :------ | ------------------------------------------------------------ |
| code              | integer | 返回状态码<br />200 正常返回                                 |
| message           | string  | 返回信息                                                     |
| biz_type          | string  | 业务类型：<br />store_list<br />store_filtered_list<br />store_search<br />store_filtered_search |
| data              | json    | 返回数据                                                     |
| data.total        | integer | 请求结果总条数                                               |
| data.current_page | integer | 请求结果分页当前页数（计数从1开始）                          |
| data.last_page    |         | 请求结果分页最后一页页数（计数从1开始）                      |
| data.per_page     | integer | 分页每页显示数量                                             |
| data.from         | integer | 当前分页结果起始元素在结果列表中的位置（计数从1开始）        |
| data.to           |         | 当前分页结果结束元素在结果列表中的位置（计数从1开始）        |
| data.page_size    | integer | 当前分页结果数量                                             |
| data.data         | list    | 当前分页结果列表，列表元素包含字段:<br />id：   item_id<br />type： item类型 |
| rec_data          | json    | 搜索结果为空时的推荐数据（仅当用户通过关键词搜索结果为空时有数据） |
| rec_data.total    | integer | 搜索无结果页推荐数据个数                                     |
| rec_data.data     | list    | 搜索无结果页推荐列表，列表元素包含字段:<br />id：   item_id<br />type： item类型 |



**返回示例1**

``` json
  {
    "code": 200,
    "message": "SUCCESS",
    "biz_type":"store_search",
    "data": {
        "total": 108,
        "current_page": 1,
        "last_page":11,
        "per_page": 10,
        "from": 1,
        "to": 10,
        "page_size": 10,
        "data":[
            {
                "id" : 73,
                "type" : "store"
            },
            {
                "id" : 60,
                "type" : "store"
            },
            {
                "id" : 132,
                "type" : "store"
            },
            {
                "id" : 113,
                "type" : "store"
            },
            {
                "id" : 89,
                "type" : "store"
            },
            {
                "id" : 64,
                "type" : "store"
            },
            {
                "id" : 65,
                "type" : "store"
            },
            {
                "id" : 66,
                "type" : "store"
            },
            {
                "id" : 67,
                "type" : "store"
            },
            {
                "id" : 68,
                "type" : "store"
            }
        ]
    }
  }
```

**返回示例2**

```json
{
    "code": 200,
    "message": "SUCCESS",
    "biz_type":"store_search",
    "data": {
        "total": 0
    },
    "rec_data": {
        "total": 5,
        "data":[
            {
                "id" : 73,
                "type" : "store"
            },
            {
                "id" : 60,
                "type" : "store"
            },
            {
                "id" : 132,
                "type" : "store"
            },
            {
                "id" : 113,
                "type" : "store"
            },
            {
                "id" : 89,
                "type" : "store"
            }
        ]
    }
}
```



  **备注** 

- 更多返回错误代码请看首页的错误代码描述



# 线索列表接口

**简要描述： **

- 线索列表接口

  包含关键词搜索、条件筛选、综合排序及条件排序

**请求URL：** 

- ` http://xx.com/search/clue_search `

**请求方式：**

- GET 

请求参数说明：**

| 参数名              | 必填 | 类型   | 说明                                                         |
| :------------------ | :--- | :----- | ------------------------------------------------------------ |
| **uid**             | 是   | string | 用户uid                                                      |
| params.**page**     | 是   | int    | 分页参数，当前页数（从1开始计数）                            |
| params.**per_page** | 是   | int    | 分页参数，每页返回结果数量                                   |
| params.money        | 否   | string | 筛选字段，投放预算区间                                       |
| params.class        | 否   | int    | 筛选字段，线索分类id（一级类目）                             |
| params.time         | 是   | string | 筛选字段，发布时间区间                                       |
| params.type         | 是   | int    | 筛选字段，客户类型id<br />1 直客公司<br />2 中间商（非4A公司）<br />3 4A公司 |
| params.data         | 是   | string | 排序条件<br />default 综合排序<br />created_at  发布时间排序<br />end_time  剩余时间排序<br />pay_number  参与者排序<br />budget_money  价格（投放预算）排序<br /><br />升降序<br />asc  升序<br />desc  降序 |
| params.title        | 是   | string | 搜索关键词                                                   |

**请求示例**

```json
{
    "uid": "123",
    "params": {
        "page": 1,
        "per_page": 10,
        "money": "0,50000",
        "class": 6,
        "time": "2020-04-12,2020-04-14 16:38:44",
        "type":1,
        "data":"end_time,asc",
        "title":"户外"
    }
}
```

 **返回参数说明** 

| 参数名            | 类型    | 说明                                                         |
| :---------------- | :------ | ------------------------------------------------------------ |
| code              | integer | 返回状态码<br />200 正常返回                                 |
| message           | string  | 返回信息                                                     |
| biz_type          | string  | 业务类型：<br />clue_list<br />clue_filtered_list<br />clue_search<br />clue_filtered_search |
| data              | json    | 返回数据                                                     |
| data.total        | integer | 请求结果总条数                                               |
| data.current_page | integer | 请求结果分页当前页数（计数从1开始）                          |
| data.last_page    |         | 请求结果分页最后一页页数（计数从1开始）                      |
| data.per_page     | integer | 分页每页显示数量                                             |
| data.from         | integer | 当前分页结果起始元素在结果列表中的位置（计数从1开始）        |
| data.to           |         | 当前分页结果结束元素在结果列表中的位置（计数从1开始）        |
| data.page_size    | integer | 当前分页结果数量                                             |
| data.data         | list    | 当前分页结果列表，列表元素包含字段:<br />id：   item_id<br />type： item类型 |

 

**返回示例**

``` 
  {
    "code": 200,
    "message": "SUCCESS",
    "biz_type":"clue_search",
    "data": {
        "total": 108,
        "current_page": 1,
        "last_page":11,
        "per_page": 10,
        "from": 1,
        "to": 10,
        "page_size": 10,
        "data":[
            {
                "id" : 73,
                "type" : "clue"
            },
            {
                "id" : 60,
                "type" : "clue"
            },
            {
                "id" : 132,
                "type" : "clue"
            },
            {
                "id" : 113,
                "type" : "clue"
            },
            {
                "id" : 89,
                "type" : "clue"
            },
            {
                "id" : 64,
                "type" : "clue"
            },
            {
                "id" : 65,
                "type" : "clue"
            },
            {
                "id" : 66,
                "type" : "clue"
            },
            {
                "id" : 67,
                "type" : "clue"
            },
            {
                "id" : 68,
                "type" : "clue"
            }
        ]
    }
  }
```

**备注** 

- 更多返回错误代码请看首页的错误代码描述
