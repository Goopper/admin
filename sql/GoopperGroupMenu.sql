-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小组/班级管理', '2000', '1', '/system/GoopperGroup', 'C', '0', 'system:GoopperGroup:view', '#', 'admin', sysdate(), '', null, '小组/班级管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小组/班级管理查询', @parentId, '1',  '#',  'F', '0', 'system:GoopperGroup:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小组/班级管理新增', @parentId, '2',  '#',  'F', '0', 'system:GoopperGroup:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小组/班级管理修改', @parentId, '3',  '#',  'F', '0', 'system:GoopperGroup:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小组/班级管理删除', @parentId, '4',  '#',  'F', '0', 'system:GoopperGroup:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('小组/班级管理导出', @parentId, '5',  '#',  'F', '0', 'system:GoopperGroup:export',       '#', 'admin', sysdate(), '', null, '');
