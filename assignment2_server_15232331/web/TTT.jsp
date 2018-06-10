<%-- 
    Document   : TTT
    Created on : 4/06/2018, 5:17:31 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Naughts & Crosses</title>
        <script src="jquery-3.3.1.js"></script>
        <script language="javascript" type="text/javascript">
            function user() {
                $.post("istart", function(){
                    $("#board").load("state?format=txt");                    
                });
        
                document.getElementById("user").style.display = "none";
                document.getElementById("computer").style.display = "none";
                document.getElementById("x").style.display = "block";
                document.getElementById("y").style.display = "block";
                document.getElementById("sub").style.display = "block";
                document.getElementById("text").style.display = "block";
                
            }
            
            function computer() {
                $.post("ustart", function() {
                    $("#board").load("state?format=txt");                    
                });
                
                document.getElementById("user").style.display = "none";
                document.getElementById("computer").style.display = "none";
                document.getElementById("x").style.display = "block";
                document.getElementById("y").style.display = "block";
                document.getElementById("sub").style.display = "block";
                document.getElementById("text").style.display = "block";
            }
            
            function submit(x, y) {
                $.post("move", {"move": "x"+x+"y"+y}, function(data, status, xhr) {
                    
                    if(xhr.status === 200) {
                        $.get("won", function(data, status, xhr) {
                           if(data.trim() === "User") {
                               alert("You have won!");
                               location.reload();
                           } else if (data.trim() === "Computer") {
                               alert("You have lost!");
                               location.reload();
                           } else if (data.trim() === "Draw") {
                               alert("It is a draw!");
                               location.reload();
                           } else {
                               $("#board").load("state?format=txt");
                           }
                        });
                    }                            
                });
            }
            
            function validate() {
                var xVal = document.getElementById("x").value;
                var yVal = document.getElementById("y").value;   
                if(xVal !== "" && yVal !== "") {
                    var pattern = /[0-2]/;
                    if(pattern.test(xVal) && pattern.test(yVal)) {
                        $.get("possiblemoves", function(data, status, xhr) {
                            var positions = data.split("\n");
                            console.log(positions);
                            for(i = 0; i < positions.length; i++) {                                
                                if(positions[i][0] === xVal && positions[i][2] === yVal) {
                                    submit(xVal, yVal);
                                }
                            }
                        });
                    } else {
                        alert("Invalid Entry");
                    }
                } else {
                    alert("Enter a position");
                }
            }
        </script>
        
    </head>
    <body>
        <h1>Naughts & Crosses</h1>
        <!-- Needs to be able to validate correct coordinate
             This includes incorrect formate and board is full -->
        <p id="board">Select who start</p>
        <button type="sumbit" name="user" id="user" onclick="user()">User Starts</button>
        <button type="sumbit" name="computer" id="computer" onclick="computer()">Computer Starts</button>
        
        </br>
        <p id="text" style="display:none">Enter a number from between 0 and 2 (0 equaling the first box) in each textbox</p>            
        <input type="text" name="x" id="x" placeholder="x position" style="display:none">
        <input type="text" name="y" id="y" placeholder="y position" style="display:none"></br>
        <button type="submit" name="sub" id="sub" onclick="validate()" style="display:none">Submit</button>
        
    </body>
</html>
