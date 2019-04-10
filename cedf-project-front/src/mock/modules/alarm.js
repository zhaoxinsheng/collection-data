import isInteger from 'lodash/isInteger'

export function listtips () {
  return {
    // isOpen: false,
    url: 'collect/tips/listtips',
    type: 'get',
    data: {
      'msg': 'success',
      'code': 0,
      'page': {
        'totalCount': 2,
        'pageSize': 10,
        'totalPage': 1,
        'currPage': 1,
        'list': [{
          'tipDesc': '运行压力异常，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
          'tipTime': '2018-07-10 10:20:24'
        },
          {
            'tipDesc': '流量异常，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
            'tipTime': '2018-07-15 11:20:00'
          }]
      }
    }
  }
}
export function listcancel () {
  return {
    // isOpen: false,
    url: 'collect/tips/listcancel',
    type: 'get',
    data: {
      'msg': 'success',
      'code': 0,
      'page': {
        'totalCount': 2,
        'pageSize': 10,
        'totalPage': 1,
        'currPage': 1,
        'list': [{
          'tipDesc': '排气温度过高，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
          'tipTime': '2018-07-12 10:30:24'
        },
          {
            'tipDesc': '流量异常，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
            'tipTime': '2018-07-20 11:30:00'
          }]
      }
    }
  }
}

export function listnormal () {
  return {
    // isOpen: false,
    url: 'collect/tips/listcancel',
    type: 'get',
    data: {
      'msg': 'success',
      'code': 0,
      'page': {
        'totalCount': 2,
        'pageSize': 10,
        'totalPage': 1,
        'currPage': 1,
        'list': [{
          'tipDesc': '电费是否发生变化，如果是，请对电费参数重新设置',
          'tipTime': '2018-07-12 10:30:24',
          'quartz': '每三个月一次'
        },
          {
            'tipDesc': '压力露点异常,机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
            'tipTime': '2018-10-12 10:30:00',
            'quartz': '每分钟一次'
          }]
      }
    }
  }
}

