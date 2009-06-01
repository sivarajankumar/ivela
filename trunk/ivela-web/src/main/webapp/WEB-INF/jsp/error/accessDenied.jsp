<script>
    var loc = document.location.toString();

    if (loc.lastIndexOf('/admin/') != -1) {
        document.location = '../index.jsp';
    } else {
        document.location = 'index.jsp';
    }
</script>