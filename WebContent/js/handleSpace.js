function comm_trimAllInputs(){
    var inputs = document.getElementsByTagName('input');
   
    for(var i=0;i<inputs.length;i++)
    {
          var obj = inputs[i];
          if(!obj.type || obj.type.toLowerCase()!='text') continue;
          obj.value = $.trim(obj.value);
    }
    var textareas = document.getElementsByTagName('textarea');
   
    for(var i=0;i<textareas.length;i++)
    {
          var obj = textareas[i];
          if(typeof(obj)!='object') continue;
          obj.value = $.trim(obj.value);
    }
}
