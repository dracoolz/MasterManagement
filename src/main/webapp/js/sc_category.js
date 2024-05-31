/**
 * 
 */
var array = new Array();
array[''] = new Array({cd:"0", label:""});
array["1"] = [
  {cd:"1", label:"鉛筆"},
  {cd:"2", label:"ペン"},
  {cd:"3", label:"ボールペン"},
  {cd:"4", label:"中性ペン"},
  {cd:"5", label:"シャーペン"},
  {cd:"6", label:"カラーペン"}
];
array["2"] = [
  {cd:"7", label:"メモ用紙"},
  {cd:"8", label:"メモ帳"},
  {cd:"9", label:"ポストイット"},
];
array["3"] = [
  {cd:"10", label:"シール"},
  {cd:"11", label:"ステッカー"},
  {cd:"12", label:"切手"},
  {cd:"13", label:"ラベル"},
];
array["4"] = [
  {cd:"14", label:"マスキングテープ"},
  {cd:"15", label:"紙テープ"},
  {cd:"16", label:"粘着テープ"},
];
array["5"] = [
  {cd:"17", label:"印鑑・判子"},
];
array["6"] = [
  {cd:"18", label:"消しゴム"},
  {cd:"19", label:"ミニ消しゴム"},
];
array["7"] = [
  {cd:"20", label:"筆箱"},
  {cd:"21", label:"筆立て"},
];
array["8"] = [
  {cd:"22", label:"カード"},
  {cd:"23", label:"ポストカード"},
];
array["9"] = [
  {cd:"24", label:"クリップ"},
];
array["10"] = [
  {cd:"25", label:"ステープラー・ホッチキス"},
];
array["11"] = [
  {cd:"26", label:"カレンダー"},
  {cd:"27", label:"ポスター"},
];
array["12"] = [
  {cd:"28", label:"小物入れ"},
];
array["13"] = [
  {cd:"29", label:"定規"},
];
array["14"] = [
  {cd:"30", label:"飾り"},
  {cd:"31", label:"その他"},
];
array["15"] = [
  {cd:"32", label:"かわいい"},
];
array["16"] = [
  {cd:"33", label:"塩系"},
];
array["17"] = [
  {cd:"34", label:"春"},
  {cd:"35", label:"夏"},
  {cd:"36", label:"秋"},
  {cd:"37", label:"冬"},
];
array["18"] = [
  {cd:"38", label:"クリスマス"},
  {cd:"39", label:"入学"},
  {cd:"40", label:"その他"},
];

document.getElementById('bc').onchange = function(){
  sc = document.getElementById("sc");
  sc.options.length = 0
  var changedPref = bc.value;
  for (let i = 0; i < array[changedPref].length; i++) {
    var op = document.createElement("option");
    value = array[changedPref][i]
    op.value = value.cd;
    op.text = value.label;
    sc.appendChild(op);
  }
}
document.getElementById('bc1').onchange = function(){
  sc1 = document.getElementById("sc1");
  sc1.options.length = 0
  var changedPref = bc1.value;
  for (let i = 0; i < array[changedPref].length; i++) {
    var op = document.createElement("option");
    value = array[changedPref][i]
    op.value = value.cd;
    op.text = value.label;
    sc1.appendChild(op);
  }
}
document.getElementById('bc2').onchange = function(){
  sc2 = document.getElementById("sc2");
  sc2.options.length = 0
  var changedPref = bc2.value;
  for (let i = 0; i < array[changedPref].length; i++) {
    var op = document.createElement("option");
    value = array[changedPref][i]
    op.value = value.cd;
    op.text = value.label;
    sc2.appendChild(op);
  }
}
document.getElementById('bc3').onchange = function(){
  sc3 = document.getElementById("sc3");
  sc3.options.length = 0
  var changedPref = bc3.value;
  for (let i = 0; i < array[changedPref].length; i++) {
    var op = document.createElement("option");
    value = array[changedPref][i]
    op.value = value.cd;
    op.text = value.label;
    sc3.appendChild(op);
  }
}