[when] [] 小于或等于 = <=
[when] [] 是 = ==
[when] [] 名字 = name
[when] [] 年龄 = age
[when] [] - {field:\w*} = {field}
[when] 学生办找一个人 = $p:Person()
[then] 学校决定将你安排到"{className}" = $p.setClassName("{className}");
[then] 输出结果 = System.out.println($p);
