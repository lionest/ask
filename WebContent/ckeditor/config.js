/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
//高度
	config.height = 130;
//图片上传
    config.filebrowserBrowseUrl =  '/CKEditor_Finder/ckfinder/ckfinder.html' ;  
    config.filebrowserImageBrowseUrl =  '/CKEditor_Finder/ckfinder/ckfinder.html?type=Images' ;  
    config.filebrowserFlashBrowseUrl =  '/CKEditor_Finder/ckfinder/ckfinder.html?type=Flash' ;  
    config.filebrowserUploadUrl =  '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files' ;  
    config.filebrowserImageUploadUrl =  '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images' ;  
    config.filebrowserFlashUploadUrl =  '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash' ;  
    config.filebrowserWindowWidth = '1000';  
    config.filebrowserWindowHeight = '700';  
    config.language =  "zh-cn" ; 
//工具栏自定义
    config.toolbar = 'Full';
    config.toolbar_Full =
    [
        { name: 'document', items : [ 'Source'] },
        
        { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
        { name: 'paragraph', items : [ /*'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv',
        '-',*/'JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
        { name: 'insert', items : [ 'Image' ] }
    ];
    config.toolbar_Basic =
    [
        ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','-','About']
    ];
//去掉段落前的tab,space
    CKEDITOR.on( 'instanceReady', function( ev ) { with (ev.editor.dataProcessor.writer) {
    	setRules("p", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("h1", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("h2", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("h3", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("h4", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("h5", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("div", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("table", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("tr", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("td", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("iframe", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("li", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("ul", {indent : false, breakAfterOpen : false, breakBeforeClose : false} );
    	setRules("ol", {indent : false, breakAfterOpen : false, breakBeforeClose : false} ); }
    	});
    //转义危险标签
    config.protectedSource.push(/<script[\s\S]*?script>/g);
    config.protectedSource.push(/<iframe[\s\S]*?iframe>/g);
    config.protectedSource.push(/<frameset[\s\S]*?frameset>/g);
    config.forcePasteAsPlainText =true; 
};

