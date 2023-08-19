var customerDB = [
    {id: "C00-001", name: "Sachin Thamalsha", address: "Mathara", salary: 100000},
    {id: "C00-002", name: "Ranjith Perera", address: "Panadura", salary: 200000},
    {id: "C00-003", name: "Kavindu Perera", address: "Panadura", salary: 300000}
];

var itemDB = [
    {code:"I00-001",name:"Lux",qtyOnHand: 100,unitPrice: 145.00},
    {code:"I00-002",name:"Sunlight",qtyOnHand: 150,unitPrice: 345.00},
    {code:"I00-003",name:"Light Boy",qtyOnHand: 400,unitPrice: 245.00}
];
var orderDB = [];

var addToCart = [
    {
        ItemCode: "ABC-001",
        Name: "Example Item",
        UnitPrice:10,
        QtyOnHand: 10,
        Total: 100.0
    },
    {
        ItemCod: "DEF-002",
        Name: "Another Item",
        UnitPrice:10,
        QtyOnHand: 5,
        Total: 50.0
    }
];
var itemCodes = [];
var placeOrder = [
    {
        OrderId:"O00-001",
        CustomerId:"C00-001",
        ItemCode: "ABC-001",
        Total:100.0,
        Cash: 100.0,
        Balance:100.0
    },

];