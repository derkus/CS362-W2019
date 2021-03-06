/*********************************
*  Author: Derk Kieft (kieftd)
*  File: cardtest3.c testing Smithy
*
* 
* Include the following lines in makefile:
*
* cardtest3: cardtest3.c dominion.o rngs.o
*      gcc -o cardtest3 -g  cardtest3.c dominion.o rngs.o $(CFLAGS)
********************************/

#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"
#include <stdlib.h>

#define TESTCARD "smithy"

/*struct gameState { 
  int numPlayers; //number of players 
  int supplyCount[treasure_map+1];  //this is the amount of a specific type of card given a specific number. 
  int embargoTokens[treasure_map+1]; 
  int outpostPlayed; 
  int outpostTurn; 
  int whoseTurn; 
  int phase; 
  int numActions; // Starts at 1 each turn 
  int coins; // Use as you see fit! 
  int numBuys; // Starts at 1 each turn 
  int hand[MAX_PLAYERS][MAX_HAND]; 
  int handCount[MAX_PLAYERS]; 
  int deck[MAX_PLAYERS][MAX_DECK]; 
  int deckCount[MAX_PLAYERS]; 
  int discard[MAX_PLAYERS][MAX_DECK]; 
  int discardCount[MAX_PLAYERS]; 
  int playedCards[MAX_DECK]; 
  int playedCardCount; 
};*/


int main() {
    int newCards = 0;
    int discarded = 1;
    //int xtraCoins = 0;
    int shuffledCards = 0;


    int handpos = 0,  bonus = 0, choice1 = 0, choice2 = 0, choice3 = 0;
    //int remove1, remove2;
    int seed = 3000;
    int numPlayers = 2;
    int thisPlayer = 0;
	struct gameState G, testG;
	int k[10] = {adventurer, gardens, village, minion, mine, cutpurse,
			sea_hag, tribute, smithy, council_room};

	// initialize a game state and player cards
	initializeGame(numPlayers, k, seed, &G);


	printf("----------------- Test Card is %s ----------------\n", TESTCARD);

	// copy the game state to a test case for later comparison
	memcpy(&testG, &G, sizeof(struct gameState));
	cardEffect(steward, choice1, choice2, choice3, &G, handpos, &bonus);
    //ref_smithy(&G, handpos, &bonus);

    //Set expected changes
    //+3 Cards
    newCards = 3;

if (G.numPlayers == testG.numPlayers)  {
    printf("PASS: #Players = %d, expected = %d\n", G.numPlayers, testG.numPlayers);
}
else {
    printf("FAIL: #Players = %d, expected = %d\n", G.numPlayers, testG.numPlayers);
}
 
if (G.whoseTurn == testG.whoseTurn)
    {
    printf("PASS: whoseTurn = %d, expected = %d\n", G.whoseTurn, testG.whoseTurn);
    }
else {
    printf("FAIL: whoseTurn = %d, expected = %d\n", G.whoseTurn, testG.whoseTurn);
}

if (G.phase == testG.phase)
    {
    printf("PASS: phase = %d, expected = %d\n", G.phase, testG.phase);
    }
else {
    printf("FAIL: phase = %d, expected = %d\n", G.phase, testG.phase);
}

if (G.numActions == testG.numActions-1)
    {
    printf("PASS: numActions = %d, expected = %d\n", G.numActions, testG.numActions-1);
    }
else {
    printf("FAIL: numActions = %d, expected = %d\n", G.numActions, testG.numActions-1);
}

if (G.coins == testG.coins)
    {
    printf("PASS:coins = %d, expected = %d\n", G.coins, testG.coins);
    }
else {
    printf("FAIL:coins = %d, expected = %d\n", G.coins, testG.coins);
}
 
if (G.phase == testG.phase)
    {
    printf("PASS: phase = %d, expected = %d\n", G.phase, testG.phase);
    }
else {
    printf("FAIL: phase = %d, expected = %d\n", G.phase, testG.phase);
}

/*G.hand[MAX_PLAYERS][MAX_HAND]; */
 
if (G.handCount[thisPlayer] == testG.handCount[thisPlayer] + newCards - discarded)  //Draw three
    {
    printf("PASS: hand count = %d, expected = %d\n", G.handCount[thisPlayer], testG.handCount[thisPlayer] + newCards - discarded);
    }
else {
    printf("FAIL: hand count = %d, expected = %d\n", G.handCount[thisPlayer], testG.handCount[thisPlayer] + newCards - discarded);
    
}

/*G.deck[MAX_PLAYERS][MAX_DECK]; */

if (G.deckCount[thisPlayer] == testG.deckCount[thisPlayer])
    {
    printf("PASS: deck count = %d, expected = %d\n", G.deckCount[thisPlayer], testG.deckCount[thisPlayer] - newCards + shuffledCards);
    }
else {
    printf("FAIL: deck count = %d, expected = %d\n", G.deckCount[thisPlayer], testG.deckCount[thisPlayer] - newCards + shuffledCards);
    
}

/*G.discard[MAX_PLAYERS][MAX_DECK]; */

if (G.discardCount[thisPlayer] == testG.discardCount[thisPlayer]-1)  //Card is discarded
    {
    printf("PASS: discardCount = %d, expected = %d\n", G.discardCount[thisPlayer],testG.discardCount[thisPlayer]-1);
    }
else {
    printf("FAIL: discardCount = %d, expected = %d\n", G.discardCount[thisPlayer],testG.discardCount[thisPlayer]-1);
}

/*G.playedCards[MAX_DECK]; */

if (G.playedCardCount+1 == testG.playedCardCount)
    {
    printf("PASS: playedCardCount = %d, expected = %d\n", G.playedCardCount+1, testG.playedCardCount);
    }
else {
    printf("FAIL: playedCardCount = %d, expected = %d\n", G.playedCardCount+1, testG.playedCardCount);
}


    return 0;
}
