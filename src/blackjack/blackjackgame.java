//Ben Olson
//PC
//1-27-17

//package blackjack;

//import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;


class blackjackgame 
{
	public static void main(String[] args)
	{
		Deck deck = new Deck();
		int playerNum = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to BlackJack! How many Players?");
		playerNum = s.nextInt();
		String cmd = " "; // <-- Kenny did dis

		while (playerNum <= 0 || playerNum > 5) 
		{
			System.out.println("Unusable amount of players! Please input another amount between 0-5!");
			playerNum = s.nextInt();
			// quick check for within range
		}
		System.out.print("Please Wait...");
		System.out.print(".");

		System.out.print(".");
		System.out.print(".");

		deck.shuffleDeck();

		// Creating a list of player hands
		ArrayList<Player> players = new ArrayList<Player>((playerNum));
		for (int i = 0; i <=(playerNum); i++)
		{
			players.add(new Player());
		}

		System.out.println("");
		System.out.println("\nThe deck has been shuffled, the players have been readied. Lets Play!");
		System.out.println("Rules are simple, Play to 21. Go over and you bust, go under and you might lose!");
		System.out.println("Good Luck and Have Fun!");

		//for loop to run through players and deal cards
		
		for(int i = players.size()-1; i>=0; i--)
		{
			deck.dealCard(players.get(i));
			deck.dealCard(players.get(i));
		}
		
		System.out.println();
		System.out.println("Here are the hands currently:");
		for(int i=players.size()-1;i>=0;i--)
		{
			System.out.println("");
			if(i==0)
			{
				System.out.println("Dealer:");
				players.get(0).printHand();
			}
			else
			{
				System.out.println("Player "+ i + "'s Hand:");
				players.get(i).printHand();
			}
		}
		
		for(int i=players.size()-1;i>0;i--)
		{
			//for loop cycles through players top down
			//because dealer is position zero

			cmd = " ";
			while((!cmd.equals("stand")) && (!cmd.equals("bust")))
			{
				System.out.println();
				System.out.println("Player "+ i +" Please select either Hit or Stand by typing H or S.");
				cmd = s.next();
				switch(cmd)
				{
					case "s":	
						cmd = "stand"; 
						break;
						
					case "h":
						cmd = dealCard(deck, players.get(i));
						break;
						
					case "S": 	
						cmd = "stand"; 
						break;
						
					case "H": 
						cmd = dealCard(deck, players.get(i));
						break;
						
					default:
						System.out.println("Invaild input...");
						break;
				}
				
				System.out.println("Player "+i+"'s Hand valuse is: " + players.get(i).getHandValue());
					
			}
			//String var needs to be set back to a default after player while loop
			System.out.println("Player "+i+"'s Hand is:");
			players.get(i).printHand();
			cmd = " ";
		}
		
		
		System.out.println(" ");
		System.out.println("The players have all played, now its the dealers turn...");
		System.out.println(" ");
		System.out.println("Dealer's Hand is:");
		while(players.get(0).getHandValue()<18)
		{
			//deck.dealCard(players.get(0)); //deal a card to the dealer
			//players.get(0).printHand(); //show the card
			dealCard(deck, players.get(0));

		}
		players.get(0).printHand(); //show the card
		System.out.println("Dealer's hand value is: " + players.get(0).getHandValue());


		// Check players for winner...
		ArrayList<String> winners = new ArrayList<String>();
		ArrayList<String> tie = new ArrayList<String>();
		ArrayList<String> losers = new ArrayList<String>();
		
		for(int i=players.size()-1;i>0;i--)
		{
			if(!players.get(i).getisOut())
			{
				if(players.get(0).getisOut())
					winners.add(("Player "+ i + " "));
				else if(players.get(i).getHandValue() > players.get(0).getHandValue())
					winners.add(("Player "+ i + " "));
				else if(players.get(i).getHandValue() == players.get(0).getHandValue())
					tie.add(("Player "+ i + " "));// if player ties with dealer bet is returned...
				else if(players.get(i).getHandValue() < players.get(0).getHandValue())
					losers.add(("Player "+ i + " "));
					
			}
			else 
			{
				losers.add(("Player "+ i + " "));
			}
		}
		
		System.out.println("");
		System.out.println("Winners are: ");
		
		for(String r : winners)
		{
			System.out.println(r);
		} // try to iterate through the arraylists
		
		System.out.println("");
		System.out.println("Ties are: ");
		
		for(String r : tie)
		{
			System.out.println(r);
		}
		
		System.out.println("");
		System.out.println("Losers are: ");
		
		for(String r : losers)
		{
			System.out.println(r);
		}
	}
	
	public static String dealCard(Deck deck, Player p)
	{
		deck.dealCard(p);
		if(p.getHandValue()>21)
		{
			System.out.println("You Bust!");
			p.setisOut(true);
			return "bust";
		}
		return " "; 
	}
	
	static void Game()
	{
		String[] args= new String[6];
		String Q = " ";
		boolean gameStatus=true;
		Scanner s = new Scanner(System.in);
		while(gameStatus == true)
		{
			blackjackgame.main(args);
			
			System.out.println("Would you like to play again?");
			System.out.println();
			Q = s.next();
			if(Q.equals("no")||Q.equals("n")||Q.equals("NO")||Q.equals("No"))
			{
				gameStatus = false;
				GameServer.main(args);
			}
		}
		s.close();
	}	

}
