<%--
  Created by IntelliJ IDEA.
  User: 丿时倾
  Date: 2020/7/4
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>

<script type="text/javascript" src="js/jquery.js"></script>
<script>
    $(function () {
        $.get("fanhui.do",function (data) {
            if (data.flag){
                // location.href="admin/ownMsg.html";\
                window.history.go(-1);
            }
        },"json")
    })
</script>

