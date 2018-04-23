
simple no formula based design:
-------------------------------
-dumb weight based
---game adjusts its strategy based on previous games, storing the data in a text file "WeightsDatabase"<br>
-weight based implementation from https://github.com/Mateo-S/Tic-Tac-Toe-ML.git<br>
-smart weight based
--training with "tic-tac-toe.data.txt" images for patterns<br>
---pattern recognition by classification:<br>
----positive results<br>
-----every possible state is a category<br>
-----images are ranked using weight<br>
-----pattern matching strings starting with..<br>
-----pattern matching strings matching values in played positions<br>
-----randomly picking best available positions<br>
-----un-predictable results<br> 
-back propagation:<br>
-same as smart program and game results influence future moves based on win or loss<br>

supervised learning:
--------------------
-simple weight based<br>
--aggregates weights for each position<br>
--game adjusts its strategy based on previous games<br>
-image, convolutional neural network CNN TODO<br>
--generate all possible plays (ideally we need a dataset of expert moves for a given board pattern)<br>
--capture image of board on every move and feed it to CNN <br>

un-supervised learning:  TODO
---------------------
-4 layers of neural networks looking for patterns<br>
--first layer<br>
---if player has a final winning move<br>
--second layer<br>
---if opponent has a final winning move<br>
--third layer<br>
---best possible move<br>
--fourth layer<br>
---classification of board position before move<br>

reinforcement learning - winning probability:  TODO
---------------------------------------------
-same as supervised learning and<br> 
-check every possible moves<br>
--predict winner<br>

reinforcement learning - back propagation:  TODO
------------------------------------------