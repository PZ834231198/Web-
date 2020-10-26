function goDownload() {
    CheckPage.action="GetDownloadList.do";
    CheckPage.submit();
}

function goUserManage() {
    CheckPage.action="UserManage.do";
    CheckPage.submit();
}

function goResManage() {
    CheckPage.action="ResManage.do";
    CheckPage.submit();
}

function goPersonCenter() {
    CheckPage.action="PersonCenter.do";
    CheckPage.submit();
}

function exit() {
    out.action="Logout.do";
    out.submit();
}

function returnBack() {
    window.history.back(-1);
}

