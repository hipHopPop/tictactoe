
supervised learning:
--------------------
-dumb weight based
------------------
---game adjusts its strategy based on previous games, storing the data in a text file "WeightsDatabase"<br>
-weight based implementation from https://github.com/Mateo-S/Tic-Tac-Toe-ML.git<br>

-smart weight based
-------------------
-training with "tic-tac-toe.data.txt" images for patterns<br>
--pattern recognition by classification:<br>
---positive results<br>
----every possible state is a category<br>
----images are ranked using weight<br>

-back propagation:
-------------------
-same as smart supervised learning and<br> 
-game results influence future moves based on win or loss<br>

unsupervised learning:  TODO
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