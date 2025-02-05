document.querySelectorAll('tr[data-href]').forEach(row => {
  row.addEventListener('click', () => {
    window.location = row.dataset.href;
  });
});

let btn = document.getElementById('btn');

const name= document.getElementById('name');
const description= document.getElementById('description');
const date= document.getElementById('date');
const currentUrl = window.location.href;

const filter= document.getElementById('filter');
const filter2= document.getElementById('filter2');
const type =document.getElementById('type').textContent;

if(type=="name" || type=="description" || type=="date") {
   filter.style.display='none';
   filter2.style.display='inline-block';
}

if (currentUrl.includes("NameAsc") ) {
name.textContent='Name \u2B63';
description.textContent='Description';
date.textContent='Date';
}else if (currentUrl.includes("NameDesc")) {
 name.textContent='Name \u2B61';
 description.textContent='Description';
 date.textContent='Date';
 } else if (currentUrl.includes("DescriptionAsc")) {
   name.textContent='Name ';
   description.textContent='Description \u2B63';
   date.textContent='Date';
   }else if (currentUrl.includes("DescriptionDesc")) {
    name.textContent='Name ';
    description.textContent='Description \u2B61';
    date.textContent='Date';
    } else if (currentUrl.includes("DateAsc")) {
         name.textContent='Name';
         description.textContent='Description';
         date.textContent='Date \u2B63';
         }else if (currentUrl.includes("DateDesc")) {
          name.textContent='Name';
          description.textContent='Description';
          date.textContent='Date \u2B61';
          }  else if (currentUrl.includes("journals")) {
  name.textContent='Name \u2B83';
  description.textContent='Description \u2B83';
  date.textContent='Date \u2B83';
  }


function f1() {
if(type=="" && currentUrl == "http://localhost:8888/journals" ) {
window.location ='journalsNameAsc';
} else if(currentUrl == "http://localhost:8888/journalsNameAsc" && type=="") {
window.location ='journalsNameDesc';
} else if (currentUrl == "http://localhost:8888/journalsNameDesc" && type=="" ) {
window.location ='journals';
}
}

function f2() {
if(type=="" && currentUrl == "http://localhost:8888/journals") {
window.location ='journalsDescriptionAsc';
}else if(currentUrl == "http://localhost:8888/journalsDescriptionAsc" && type=="") {
window.location ='journalsDescriptionDesc';
} else if (currentUrl == "http://localhost:8888/journalsDescriptionDesc" && type=="") {
window.location ='journals';
}
}

function f3() {
if(type=="" && currentUrl == "http://localhost:8888/journals") {
window.location ='journalsDateAsc';
} else if(currentUrl == "http://localhost:8888/journalsDateAsc" && type=="") {
window.location ='journalsDateDesc';
} else if (currentUrl == "http://localhost:8888/journalsDateDesc" && type=="") {
window.location ='journals';
}
}



function changeButtonState(checkbox) {
            var btn = document.getElementById('invisible');
            if (checkbox.checked) {
                btn.style.display='block';
            } else {
                btn.style.display='none';
            }
        }



