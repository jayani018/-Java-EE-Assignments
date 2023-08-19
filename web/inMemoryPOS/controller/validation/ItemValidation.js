// validation for item
const ITEM_CODE_REGEX = /^(I00-)[0-9]{3}$/;
const ITEM_NAME_REGEX = /^[A-Za-z ]{3,}$/;
const ITEM_QTY_REGEX = /^[0-9]+$/;
const ITEM_PRICE_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

//add validations and text fields to the
let i_vArray = new Array();
i_vArray.push({field: $("#itemCode1"), regEx: ITEM_CODE_REGEX});
i_vArray.push({field: $("#itemName1"), regEx: ITEM_NAME_REGEX});
i_vArray.push({field: $("#itemQuantity"), regEx: ITEM_QTY_REGEX});
i_vArray.push({field: $("#itemUnitPrice"), regEx: ITEM_PRICE_REGEX});

function clearItemInputFields() {
    $("#itemCode1,#itemName1,#itemQuantity,#itemUnitPrice").val("");
    $("#itemCode1,#itemName1,#itemQuantity,#itemUnitPrice").css("border", "1px solid #ced4da");
    $("#itemCode1").focus();
    // setItemBtn();
}

// setItemBtn();

//disable tab
$("#itemCode1,#itemName1,#itemQuantity,#itemUnitPrice").on("keydown keyup", function (e) {
    //get the index number of data input fields indexNo
    let indexNo = i_vArray.indexOf(i_vArray.find((c) => c.field.attr("id") == e.target.id));

    //Disable tab key
    if (e.key == "Tab") {
        e.preventDefault();
    }

    //check validations
    checkItemValidations(i_vArray[indexNo]);

    // setItemBtn();

    //If the enter key pressed cheque and focus
    if (e.key == "Enter") {

        if (e.target.id != i_vArray[i_vArray.length - 1].field.attr("id")) {
            //check validation is ok
            if (checkItemValidations(i_vArray[indexNo])) {
                i_vArray[indexNo + 1].field.focus();
            }
        } else {
            if (checkItemValidations(i_vArray[indexNo])) {
                saveItem();
            }
        }
    }
});


function checkItemValidations(object) {
    if (object.regEx.test(object.field.val())) {
        setItemBorder(true, object)
        return true;
    }
    setItemBorder(false, object)
    return false;
}

function setItemBorder(bol, ob) {
    if (!bol) {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid red");
        } else {
            ob.field.css("border", "1px solid #ced4da");
        }
    } else {
        if (ob.field.val().length >= 1) {
            ob.field.css("border", "2px solid green");
        } else {
            ob.field.css("border", "1px solid #ced4da");
        }
    }

}

function checkAllItems() {
    for (let i = 0; i < i_vArray.length; i++) {
        if (!checkItemValidations(i_vArray[i])) return false;
    }
    return true;
}

// function setItemBtn() {
//     $("#deleteBtn").prop("disabled", true);
//     $("#updateBtn").prop("disabled", true);
//
//     if (checkAllItems()) {
//         $("#saveBtn").prop("disabled", false);
//     } else {
//         $("#saveBtn").prop("disabled", true);
//     }
//
//     let code = $("#itemCode1").val();
//     if (searchItem(code) == undefined) {
//         $("#deleteBtn").prop("disabled", true);
//         $("#updateBtn").prop("disabled", true);
//     } else {
//         $("#deleteBtn").prop("disabled", false);
//         $("#updateBtn").prop("disabled", false);
//     }
//
// }

