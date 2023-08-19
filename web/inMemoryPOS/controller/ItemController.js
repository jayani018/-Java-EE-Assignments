//load all existing items
getAllItems();

//add item event
$("#saveBtn").click(function () {
    if (checkAllItems()){
        saveItem();
    }

});

//get all items event
$("#getAllBtn").click(function () {
    getAllItems();
});

//bind tr events for getting back data of the rows to text fields
function bindItemTrEvents() {
    $('#itemTableBody>tr').click(function () {
        //get the selected rows data
        let code = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let qty = $(this).children().eq(2).text();
        let unitPrice = $(this).children().eq(3).text();

        //set the selected rows data to the input fields
        $("#itemCode1").val(code);
        $("#itemName1").val(name);
        $("#itemQuantity").val(qty);
        $("#itemUnitPrice").val(unitPrice);
    })
}

//delete btn event
$("#deleteBtn").click(function () {
    let code = $("#itemCode1").val();

    let consent = confirm("Do you want to delete.?");
    if (consent) {
        let response = deleteItem(code);
        if (response) {
            alert("Item Deleted");
            clearItemInputFields();
            getAllItems();
        } else {
            alert("Item Not Removed..!");
        }
    }


});

//update  btn event
$("#updateBtn").click(function () {
    let code = $("#itemCode1").val();
    updateItem(code);
    clearItemInputFields();
});

//clear btn event
// $("#btn-clear").click(function () {
//     clearItemInputFields();
// });



// CRUD operation Functions
function saveItem() {
    let itemCode = $("#itemCode1").val();
    //check item is exists or not?
    if (searchItem(itemCode.trim()) == undefined) {

        //if the customer is not available then add him to the array
        let itemName = $("#itemName1").val();
        let itemQty = $("#itemQuantity").val();
        let itemPrice = $("#itemUnitPrice").val();

        //by using this one we can create a new object using
        //the item model with same properties
        let newItem = Object.assign({}, item);
        newItem.code = itemCode;
        newItem.name = itemName;
        newItem.qtyOnHand = itemQty;
        newItem.unitPrice = itemPrice;

        //add item record to the item array (DB)
        itemDB.push(newItem);
        console.table(itemDB);
        updateSelectorItem();
        clearItemInputFields();
        getAllItems();

    } else {
        alert("Item already exits.!");
        clearItemInputFields();
    }
}

function getAllItems() {
    //clear all tbody data before add
    $("#itemTableBody").empty();

    //get all items
    for (let i = 0; i < itemDB.length; i++) {
        let code = itemDB[i].code;
        let name = itemDB[i].name;
        let qty = itemDB[i].qtyOnHand;
        let unitPrice = itemDB[i].unitPrice;

        let row = `<tr>
                     <td>${code}</td>
                     <td>${name}</td>
                     <td>${qty}</td>
                     <td>${unitPrice}</td>
                    </tr>`;

        // //and then append the row to tableBody
        $("#itemTableBody").append(row);

        //invoke this method every time
        // we add a row // otherwise click
        //event will not work
        bindItemTrEvents();
    }
}

function deleteItem(code) {
    for (let i = 0; i < itemDB.length; i++) {
        if (itemDB[i].code == code) {
            itemDB.splice(i, 1);
            return true;
        }
    }
    return false;
}

function searchItem(code) {
    return itemDB.find(function (item) {
        //if the search code match with item record
        //then return that object
        return item.code == code;
    });
}

function updateItem(code) {
    if (searchItem(code) == undefined) {
        alert("No such Item..please check the Code");
    } else {
        let consent = confirm("Do you really want to update this Item.?");
        if (consent) {
            let item = searchItem(code);
            //if the item available can we update.?

            let itemName = $("#itemName1").val();
            let itemQty = $("#itemQuantity").val();
            let itemPrice = $("#itemUnitPrice").val();

            item.name = itemName;
            item.qtyOnHand = itemQty;
            item.unitPrice = itemPrice;

            getAllItems();
        }
    }

}

function clearItemInputFields() {
    $("#itemCode1,#itemName1,#itemQuantity,#itemUnitPrice").val("");
    $("#itemCode1,#itemName1,#itemQuantity,#itemUnitPrice").css("border", "1px solid #ced4da");
    $("#itemCode1").focus();
    setItemBtn();
}



