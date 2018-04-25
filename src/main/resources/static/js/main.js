'use strict';
const newList = () => {
    let catList = document.getElementById('catList');
    let category = document.getElementById('category').value;
    let listItem = document.createElement('li');
    listItem.textContent = category;
    catList.appendChild(listItem);
    console.log(catList);
    return catList;
};

const addBtn = document.getElementById('addBtn');

addBtn.addEventListener('click', newList);
