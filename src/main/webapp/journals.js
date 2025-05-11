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
if(type=="" && !currentUrl.includes("NameAsc")&& !currentUrl.includes("NameDesc") ) {
window.location ='journalsNameAsc';
} else if(currentUrl.includes("Asc")&& type=="") {
window.location ='journalsNameDesc';
} else if (currentUrl.includes("Desc")&& type=="" ) {
window.location ='journals';
}
}

function f2() {
if(type=="" && !currentUrl.includes("DescriptionAsc")&& !currentUrl.includes("DescriptionDesc")) {
window.location ='journalsDescriptionAsc';
}else if(currentUrl.includes("DescriptionAsc") && type=="" ) {
window.location ='journalsDescriptionDesc';
} else if (currentUrl.includes("DescriptionDesc") && type=="") {
window.location ='journals';
}
}

function f3() {
if(type=="" && !currentUrl.includes("DateAsc")&& !currentUrl.includes("DateDesc")) {
window.location ='journalsDateAsc';
} else if(currentUrl.includes("DateAsc") && type=="" ) {
window.location ='journalsDateDesc';
} else if (currentUrl.includes("DateDesc") && type=="") {
window.location ='journals';
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



