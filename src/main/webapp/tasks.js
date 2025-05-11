const name= document.getElementById('name');
const description= document.getElementById('description');
const date= document.getElementById('date');
const status= document.getElementById('status');
const currentUrl = window.location.href;

const filter= document.getElementById('filter');
const filter2= document.getElementById('filter2');
const type =document.getElementById('type').textContent;

if(type=="name" || type=="description" || type=="date" || type=="status") {
   filter.style.display='none';
   filter2.style.display='inline-block';
}



if (currentUrl.includes("NameAsc")) {
name.textContent='Name \u2B63';
description.textContent='Description';
date.textContent='Date';
status.textContent='Status';
}else if (currentUrl.includes("NameDesc")) {
 name.textContent='Name \u2B61';
 description.textContent='Description';
 date.textContent='Date';
 status.textContent='Status';
 } else if (currentUrl.includes("DescriptionAsc")) {
   name.textContent='Name ';
   description.textContent='Description \u2B63';
   date.textContent='Date';
   status.textContent='Status';
   }else if (currentUrl.includes("DescriptionDesc")) {
    name.textContent='Name ';
    description.textContent='Description \u2B61';
    date.textContent='Date';
    status.textContent='Status';
    } else if (currentUrl.includes("DateAsc")) {
         name.textContent='Name';
         description.textContent='Description';
         date.textContent='Date \u2B63';
         status.textContent='Status';
         }else if (currentUrl.includes("DateDesc")) {
          name.textContent='Name';
          description.textContent='Description';
          date.textContent='Date \u2B61';
          status.textContent='Status';
          } else if (currentUrl.includes("StatusAsc")) {
               name.textContent='Name';
               description.textContent='Description';
               date.textContent='Date';
               status.textContent='Status \u2B63';
               }else if (currentUrl.includes("StatusDesc")) {
                name.textContent='Name';
                description.textContent='Description';
                date.textContent='Date';
                status.textContent='Status \u2B61';
                }  else if (currentUrl.includes("tasks")) {
  name.textContent='Name \u2B83';
  description.textContent='Description \u2B83';
  date.textContent='Date \u2B83';
  status.textContent='Status \u2B83';
  }


function f1(a)  {
if(type=="" && !currentUrl.includes("NameAsc")&& !currentUrl.includes("NameDesc")) {
window.location ='tasksNameAsc'+a;
} else if(currentUrl.includes("Asc")&& type=="" ) {
window.location ='tasksNameDesc'+a;
} else if (currentUrl.includes("tasksNameDesc")&& type=="") {
window.location ='tasks'+a;
}
}

function f2(a) {
if(type=="" && !currentUrl.includes("DescriptionAsc")&& !currentUrl.includes("DescriptionDesc")) {
window.location ='tasksDescriptionAsc'+a;
} else if(currentUrl.includes("tasksDescriptionAsc") && type=="" ) {
window.location ='tasksDescriptionDesc'+a;
} else if (currentUrl.includes("tasksDescriptionDesc") && type=="") {
window.location ='tasks'+a;
}
}

function f3(a) {
if (type=="" && !currentUrl.includes("DateAsc")&& !currentUrl.includes("DateDesc")) {
window.location ='tasksDateAsc'+a;
} else if(currentUrl.includes("tasksDateAsc") && type=="" ) {
window.location ='tasksDateDesc'+a;
} else if (currentUrl.includes("tasksDateDesc") && type=="") {
window.location ='tasks'+a;
}
}

function f4(a) {
if (type=="" && !currentUrl.includes("StatusAsc")&& !currentUrl.includes("StatusDesc")) {
window.location ='tasksStatusAsc'+a;
} else if(currentUrl.includes("tasksStatusAsc") && type=="") {
window.location ='tasksStatusDesc'+a;
} else if (currentUrl.includes("tasksStatusDesc") && type=="") {
window.location ='tasks'+a;
}
}

function changeButtonState(checkbox) {
            var btn = document.getElementById('invisible');
            var btn2 = document.getElementById('invisible1');
            var btn3 = document.getElementById('invisible2');
            if (checkbox.checked) {
                btn.style.display='block';
                btn2.style.display='block';
                btn3.style.display='block';
            } else {
                btn.style.display='none';
                btn2.style.display='none';
                btn3.style.display='none';
            }
        }

