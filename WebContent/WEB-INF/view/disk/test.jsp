<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
好的
num:${filenum}
size:${filesize}




<div class="pbox ng-scope pbox-open" style="top: 62px; left: 1199px;"><div class="pop-menu" ng-click="vm.close()">
    <!-- ngIf: vm.drive.type==constant.drive.type.folder --><div ng-if="vm.drive.type==constant.drive.type.folder" class="ng-scope">
        <ul>
            <!-- ngIf: vm.drive.myPrivileges|moduleDrivePrivilege:'folder_create' --><li ng-if="vm.drive.myPrivileges|moduleDrivePrivilege:'folder_create'" class="ng-scope">
                <a href="javascript:;" ng-click="vm.addFolder()">
                    <span translate="drive.ADD_FOLDER" class="ng-scope">新建文件夹</span>
                </a>
            </li><!-- end ngIf: vm.drive.myPrivileges|moduleDrivePrivilege:'folder_create' -->
            <!-- ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'folder_setting') && vm.drive.scope.belong==constant.drive.belong.team --><li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'folder_setting') &amp;&amp; vm.drive.scope.belong==constant.drive.belong.team" class="ng-scope">
                <!-- ngIf: vm.drive.scope.visibility==constant.drive.visibility.private -->
                <!-- ngIf: vm.drive.scope.visibility==constant.drive.visibility.public --><a ng-if="vm.drive.scope.visibility==constant.drive.visibility.public" href="javascript:;" ng-click="vm.updatePermissionAndVisibility()" class="ng-scope">
                    <span translate="drive.SET_PERMISSION" class="ng-scope">设置权限</span>
                </a><!-- end ngIf: vm.drive.scope.visibility==constant.drive.visibility.public -->
            </li><!-- end ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'folder_setting') && vm.drive.scope.belong==constant.drive.belong.team -->
            <!-- ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'folder_share') --><li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'folder_share')" class="ng-scope">
                <a href="javascript:;" ng-click="vm.openLink()">
                    <!--<i class="lcfont lc-link"></i>-->
                    <span class="ng-binding">公开链接</span>
                </a>
            </li><!-- end ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'folder_share') -->
            <li role="separator" class="divider"></li>
            <!-- ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'drive_move') --><li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'drive_move')" class="ng-scope">
                <a href="javascript:;" ng-click="vm.moveDrive()">
                    <!--<i class="lcfont lc-folder"></i>-->
                    <span class="ng-binding">移动</span>
                </a>
            </li><!-- end ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'drive_move') -->
            <!-- ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'drive_copy') --><li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'drive_copy')" class="ng-scope">
                <a href="javascript:;" ng-click="vm.copyDrive()">
                    <span translate="drive.COPY" class="ng-scope">复制</span>
                </a>
            </li><!-- end ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'drive_copy') -->
            <!-- ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'file_download') && !(vm.drive.addition.path|isExternalLink) && (vm.drive.type!=constant.drive.type.page) && (vm.drive.type!=constant.drive.type.mail) --><li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'file_download') &amp;&amp; !(vm.drive.addition.path|isExternalLink) &amp;&amp; (vm.drive.type!=constant.drive.type.page) &amp;&amp; (vm.drive.type!=constant.drive.type.mail)" class="ng-scope">
                <a ng-href="https://wt-box.worktile.com/drives/5aca1a069826d1629e4daf9f/package?team_id=5ac97441ca700f541543bc60&amp;version=undefined&amp;action=download" href="https://wt-box.worktile.com/drives/5aca1a069826d1629e4daf9f/package?team_id=5ac97441ca700f541543bc60&amp;version=undefined&amp;action=download">
                    <!--<i class="lcfont lc-download"></i>-->
                    <span class="ng-binding">下载</span>
                </a>
            </li><!-- end ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'file_download') && !(vm.drive.addition.path|isExternalLink) && (vm.drive.type!=constant.drive.type.page) && (vm.drive.type!=constant.drive.type.mail) -->
            <!-- ngIf: (vm.drive.myPrivileges|moduleDrivePrivilege:'folder_setting') --><li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'folder_setting')" class="ng-scope">
                <a href="javascript:;" ng-click="vm.renameDrive()">
                    <span translate="drive.RENAME" class="ng-scope">重命名</span>
                </a>
            </li><li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'folder_setting')" class="ng-scope">
                <a href="javascript:;" ng-click="vm.changeColor()">
                    <span translate="drive.MODIFY_COLOR" class="ng-scope">修改颜色</span>
                </a>
            </li>
            <li role="separator" class="divider"></li>
            <li ng-if="(vm.drive.myPrivileges|moduleDrivePrivilege:'drive_remove')" class="ng-scope">
                <a href="" ng-click="vm.deleteDrive()">
                    <span class="ng-binding">删除</span>
                </a>
            </li>
        </ul>
    </div>
</div></div>

</body>
</html>