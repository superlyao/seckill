-- 秒杀执行的存储过程
DELIMITER $$ -- console ;
-- 定义存储过程 in 输入参数 out输出参数
-- row_count() 返回上一条修改类型sql（delete，insert，update）影响的行数
create procedure `seckill`.`execute_seckill`
(in v_seckill_id bigint, in v_phone bigint, in v_kill_time timestamp , out r_result int)
begin
  declare insert_count int default 0;
  start transaction ; -- 开启一个事务
  insert ignore into success_killed (seckill_id, user_phone, create_time)
  values (v_seckill_id, v_phone, v_kill_time);
  select row_count() into insert_count;
  IF(insert_count = 0) then
    rollback;
    set r_result = -1; -- 定义返回的值当没有更新时定义值为-1
  ELSEIF(insert_count < 0) then
    rollback;
    set r_result = -2; -- 更新失败定义值为-2
  ELSE
    update seckill
    set number = number - 1
    where seckill_id = v_seckill_id
      and end_time > v_kill_time
      and start_time < v_kill_time
      and number > 0;
    select row_count() into insert_count;
    IF (insert_count = 0)then
      rollback;
      set r_result = -1;
    ELSEIF (insert_count < 0)then
      rollback;
      set r_result = -2;
    else
      commit;
      set r_result = 1;
    END IF;
  END IF;
END;
$$
-- 存储过程定义结束

DELIMITER ;
set @r_result = -3;
-- 执行存储过程
call execute_seckill(1001,13800000000, now(), @r_result);

-- 获取结果
select @r_result;

-- 1.存储过程优化，事务行级锁持有的时间
-- 2.不要过度依赖存储过程
-- 3.简单的逻辑可言应用