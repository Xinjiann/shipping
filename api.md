# api

### shipping-order/list

描述：分页查询客户查询订单

入参：
``` json
{
"openid":3,
"status":0,
"pageParam":{
"size":10,
"current":1
}
}
```


出参：

``` json
{
"code": 200,
"msg": "操作成功",
"data": {
"records": [
{
"remark": "描述",
"id": 1,
"trackingNumber": "TY123456",
"address": "aaaa",
"createTime": "2023-03-12T12:34:32.000+0000",
"openid": "3",
"price": null,
"orderNumber": null,
"image": null,
"status": 0,
"deleted": 0
},
{
"remark": "描述",
"id": 2,
"trackingNumber": "TY123456",
"address": "bbbb",
"createTime": "2023-03-12T12:35:37.000+0000",
"openid": "3",
"price": null,
"orderNumber": null,
"image": null,
"status": 0,
"deleted": 0
},
{
"remark": "描述",
"id": 3,
"trackingNumber": "TY123456",
"address": "bbbb",
"createTime": "2023-03-12T12:38:02.000+0000",
"openid": "3",
"price": null,
"orderNumber": null,
"image": null,
"status": 0,
"deleted": 0
}
],
"total": 3,
"size": 10,
"current": 1,
"orders": [],
"searchCount": true,
"pages": 1
}
}
```

### shipping-order/submit

描述：客户提交订单

入参：
```json
{
"trackingNumber": "TY123456",
"address": "bbbb",
"remark":"描述",
"openid":"3"
}
```

出参：
```json
{
"code": 200,
"msg": "操作成功",
"data": null
}
```