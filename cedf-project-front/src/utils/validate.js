/**
 * 邮箱
 * @param {*} s
 */
export function isEmail (s) {
  return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(s)
}

/**
 * 手机号码
 * @param {*} s
 */
export function isMobile (s) {
  return /^1[0-9]{10}$/.test(s)
}

/**
 * 电话号码
 * @param {*} s
 */
export function isPhone (s) {
  return /^([0-9]{3,4}-)?[0-9]{7,8}$/.test(s)
}

/**
 * URL地址
 * @param {*} s
 */
export function isURL (s) {
  return /^http[s]?:\/\/.*/.test(s)
}

/**
 * 判断值是否为空
*/
export function isBlank (s) {
   if (s == null || s == '') {
   	  return true;
   }
   return false;
}

/**
 * 判断值是否大于规定的长度
*/
export function validateLength (s,length) {
   if (s == null || s == '') {
   	  return true;
   }
   if (s.length > length){
       return false;
   }
   return true;  
}

/**
 * 判断值是否大于规定的长度
*/
export function isUserName (s) {
 
  if (s == null || s == '') {
      return true;
   }
   if (s.length > 20){
       return false;
   }
   return true;   
}

/**
 * 判断用户名长度
  用在 增加用户名
*/
export function isUserNameLength (s) {
 
  if (s == null || s == '') {
      return true;
   }
   if (s.length > 30 || s.length < 5){
       return false;
   }
   return true;   
}


/**
 * 判断值是否是密码
*/
export function isPassword (s) {
 
 var reg = /^((?=.*[a-zA-Z])|(?=.*\d)|(?=.*[~!@#$%^&*()_+`\-={}:";,.\/])).{6,18}$/;  
 return reg.test(s); 
}

/**
 判断是否是金额 10位整数 两位小数
**/
export function isAmount (s) {
  var reg = /^\d{1,10}(\.\d{1,2}){0,1}$/;
  return reg.test(s);
}

/**
 比较两个日期的大小
**/
export function validateDate(date1 ,date2) {
  
   if (date1 != null && date2 != null) {
        var year = date1.getFullYear();
        var month = date1.getMonth() + 1;
        var day = date1.getDate();

        var year2 = date2.getFullYear();
        var month2 = date2.getMonth() + 1;
        var day2 = date2.getDate();

        if (month < 10) {
            month = '0' + month;
        }
        if (day < 10) {
            day = '0' + day;
        }
        if (month2 < 10) {
            month2 = '0' + month2;
        }
        if (day2 < 10) {
            day2 = '0' + day2;
        }
        return  parseInt(year +'' + month +''+ day) > parseInt(year2 +'' + month2 +'' + day2);
     }
}

