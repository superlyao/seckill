var seckill = {
    // 封装秒杀ajax的url
    url : {
        now: function () {
            return '/seckill/time/now'
        },
        exposer: function (seckilld) {
          return '/seckill/' + seckilld + '/exposer'
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execute'
        }
    },
    handleSeckillkill:function(seckillId,node) {
        node.hide().html(
            '<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>'
        )// 按钮
        $.post(seckill.url.exposer(seckillId), {}, function (result) {
            if (result && result.success) {
                var exposer = result.data;
                if (exposer.exposed) {
                    // 开始秒杀
                    // 获取秒杀地址
                    var md5 = exposer.md5
                    var killUrl = seckill.url.execution(seckillId, md5)
                    // 绑定一次点击事件
                    $('#killBtn').one('click', function () {
                        $(this).addClass('disabled');
                        // 发送请求
                        $.post(killUrl, {}, function (result) {
                            console.log("result:", result)
                            if (result && result.success) {
                                var seckillResult = result.data
                                var state = seckillResult.state
                                var info =seckillResult.info
                                console.log("info:", info)
                                node.html('<span>' + info + '</span>')
                            }
                        })
                    })
                    node.show()
                } else {
                    // 未开启秒杀
                    var now = exposer.now;
                    var start = exposer.start;
                    var end = exposer.end;
                    // 重新计时
                    seckill.countdown(seckillId, now, start, end)
                }
            } else {
                console.log(result)
            }
        })
    },
    validatePhone: function(phone) {
      if (phone && phone.length == 11 && !isNaN(phone)) {
          return true
      }
      return false
    },
    countdown:function(seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box')
        if (nowTime > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束')
        } else if (nowTime < startTime) {
            seckillBox.html('秒杀未开始')
            var killTiem = new Date(startTime + 1000)
            seckillBox.countdown(killTiem, function (e) {
                var format = e.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒')
                seckillBox.html(format)
            }).on('finish.countdown', function () {
                // 获取秒杀地址 控制逻辑
                seckill.handleSeckillkill(seckillId, seckillBox)
            })
        } else { //秒杀开始
            seckill.handleSeckillkill(seckillId, seckillBox)
        }
    },
    // 详情页的秒杀逻辑
    detail: {
        // 详情页初始化
        init: function (params) {
            var killPhone = $.cookie('killPhone');
            var startTime = params.startTime;
            var endTime = params.endTime;
            var seckillId = params.seckillId;
            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,// 显示弹出层
                    backdrop: 'static',// 禁止位置关闭
                    keyboard: false// 关闭键盘事件
                })
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log(seckill.validatePhone(inputPhone))
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'})
                        window.location.reload()
                    }else {
                        $('#killPhoneMessage').hide().html('<lable class="label">手机号错误!</lable>').show(300)
                    }
                })
            }
            $.get(seckill.url.now(), {}, function (result) {
                if (result && result.success) {
                    var nowTime = result.data
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result)
                }
            })
        }
    }
}