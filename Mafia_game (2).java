3...................................................../*MAFIA program assignment by
  I.Nikhila Paul(18011A0519)
  B.Akshitha(18011A0510)
  V.Akanksha(18011A0550)
*/

import java.util.*;
import java.util.Random;
class players
{
	int id;
}
class mafia extends players
{
	int hp=2500;
}
class detective extends players
{
	int hp=800;
}
class healer extends players
{
	int hp=800;
}
class commoner extends players
{

	int hp=1000;
}
public class Mafia_game{
	
	
	public static void main (String[] args)
	{
        
		int N,sumop,ch,mafia_no,detective_no,healer_no,commoner_no,i,j,count=0,round=0;
		int nop,m_target,d_test,h_target,val1,som=0,thp=0,red,rem;
		int u_vote,p;
		
		Scanner an=new Scanner(System.in);
		
		
		System.out.println("WELCOME TO MAFIA");
		
		System.out.println("Enter no.of players");
		N=an.nextInt();
		
		nop=N;
		if(N<6)
		{
			System.out.println("Enter no.of players again");
			N=an.nextInt();
		}
		mafia_no=N/5;
		detective_no=N/5;
		healer_no=Math.max(1,N/10);
		commoner_no=N- mafia_no-detective_no-healer_no;
		sumop=N-mafia_no;
		
		ArrayList <Integer> alive=new ArrayList<Integer>();
		ArrayList <Integer> dead=new ArrayList<Integer>();
		dead.add(0);
		for(i=0;i<N;i++)
		{
			alive.add(i+1);
		}
		
		Vector<mafia> m_player=new Vector<mafia>(mafia_no);
		Vector<detective>d_player=new Vector<detective>(detective_no);
		Vector<healer>h_player=new Vector<healer>(healer_no);
		Vector<commoner>c_player=new Vector<commoner>(commoner_no);
		
		for(i=0;i<mafia_no;i++)
		{
			count++;
			mafia temp=new mafia();
			temp.id=count;
		    m_player.add(temp);
			
		}
		for(i=0;i<healer_no;i++)
		{
			count++;
			healer temp=new healer();
			temp.id=count;
			h_player.add(temp);
		}
		for(i=0;i<detective_no;i++)
		{
			count++;
			detective temp=new detective();
			temp.id=count;
		    d_player.add(temp);
		}
		for(i=0;i<commoner_no;i++)
		{
			count++;
			commoner temp=new commoner();
	        temp.id=count;
	        c_player.add(temp);
		}
		
		
		
		System.out.println("Choose a character\n1)Mafia\n2)Detective\n3)Healer\n4)Commoner\n5)Assign Randomly");
		ch=an.nextInt();
		
		
		switch(ch)
		{
		case 1:
			j=0;
			System.out.println("You are a mafia");
			System.out.println("You are player" + m_player.get(j).id);
			System.out.println("Other mafia are");
		
			
			for(j=1;j<m_player.size();j++)
			{
				System.out.println("player"  + m_player.get(j).id);
			}
			while(mafia_no!=sumop && mafia_no>0)
			{
				if(mafia_no>sumop)
					break;
				int val=-1;
				round++;
				System.out.println("ROUND"+round+":");
				System.out.print(nop+" Players are remaining:");
				
				
				for(i=0;i<alive.size();i++)
				{
				System.out.print("Player"+alive.get(i)+",");
				}
				System.out.println("\nChoose a target");
				m_target=an.nextInt();
				if(m_target>=1&&m_target<=N/5)
				{
				  System.out.println("you cannot target mafia");
				  System.out.println("choose again");
				  m_target=an.nextInt();
				}
				System.out.println("Detectives has choosen a player to test");
				d_test=random_gen(N,dead);
				System.out.println("Healer has choosen a player to heal");
				h_target=random_gen(N,dead);
				System.out.println("...End of actions...");
				

	                if(m_target>N/5 && m_target<=(2*N/5))
						thp=d_player.get(m_target-1-N/5).hp;
					else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
						thp=h_player.get(m_target-1-(2*N/5)).hp;
					else
						thp=c_player.get(m_target-1-healer_no-(2*N/5)).hp;
				for(i=0;i<N/5;i++)
				{
					som=m_player.get(i).hp+som;
					if(m_player.get(i).id==d_test)
						val=i+1;
				}
				
				if(val!=-1)
					System.out.println("Player"+ val+" is mafia");
				else
					System.out.println("player"+d_test +"is not mafia");
                if(h_target!=m_target)
				{
					if(som>=thp)
					{
						System.out.println("Player "+m_target+" died");
						for(i=0;i<alive.size();i++)
						{
							if(alive.get(i)==m_target)
								alive.remove(i);
						}
						dead.add(m_target);
						nop--;
						sumop--;
						red=thp/mafia_no;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						thp=0;
					}
					else 
					{
						red=thp/mafia_no;
						thp=thp-som;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
				    }
	
				}
				else if(h_target==m_target)
				{
					
						red=thp/mafia_no;
						thp=500;
						
				
		                System.out.println("No one died");
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						
				}
                if(m_target>N/5 && m_target<=(2*N/5))
					d_player.get(m_target-1-N/5).hp=thp;
				else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
					h_player.get(m_target-1-(2*N/5)).hp=thp;
				else
				   c_player.get(m_target-1-healer_no-(2*N/5)).hp=thp;
                
				System.out.println("Choose a player to vote");
				u_vote=an.nextInt();
				
				val1=votes_gen(u_vote,nop,N,dead);
				System.out.println("Player"+val1+" has been voted out");
				dead.add(val1);
				
			
		
					for(i=0;i<alive.size();i++)
					{
						if(alive.get(i)==val1)
						{
							alive.remove(i);
						    nop--;
							if(val1>=1 && val1<=N/5)
							{
								mafia_no=mafia_no-1;
								m_player.get(val1-1).hp=0;
							}
							else
							  sumop--;
						}
					}
				
				
				}
			   if(mafia_no<=0)
			   {
				System.out.println("Mafia has lost");
				 System.out.println("END OF GAME");
			   }
			   else if(mafia_no==sumop)
			   {
				System.out.println("Mafia Wins");
			   System.out.println("END OF GAME");
			   }
			   break;
			
		case 2:
			j=0;
			System.out.println("You are a Detective");
			System.out.println("You are player" + d_player.get(j).id);
			while(mafia_no!=sumop && mafia_no>0)
			{
				if(mafia_no>sumop)
				{
				  System.out.println("END GAME");
				  System.out.println("Mafia won");
				  break;
				}
				
				int val=-1;
				round++;
				System.out.println("ROUND"+round+":");
				System.out.print(nop+" Players are remaining:");
				
				
				for(i=0;i<alive.size();i++)
				{
				System.out.print("Player"+alive.get(i)+",");
			    }
                                
                System.out.println("\nMafia has chosen a player to target");
                m_target=random_gen(N,dead);
				while(m_target>=1&&m_target<=N/5)
				{
				  m_target=random_gen(N,dead);
				}
				System.out.println("Choose a player to test");
				d_test=an.nextInt();
				System.out.println("Healer has choosen a player to heal");
				h_target=random_gen(N,dead);
				System.out.println("...End of actions...");
				

	                if(m_target>N/5 && m_target<=(2*N/5))
						thp=d_player.get(m_target-1-N/5).hp;
					else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
						thp=h_player.get(m_target-1-(2*N/5)).hp;
					else
						thp=c_player.get(m_target-1-healer_no-(2*N/5)).hp;
				for(i=0;i<N/5;i++)
				{
					som=m_player.get(i).hp+som;
					if(m_player.get(i).id==d_test)
						val=i+1;
				}
				
				if(val!=-1)
					System.out.println("Player"+ val+" is mafia");
				else
					System.out.println("player"+d_test +"is not mafia");
                if(h_target!=m_target)
				{
					if(som>=thp)
					{
						System.out.println("Player "+m_target+" died");
						for(i=0;i<alive.size();i++)
						{
							if(alive.get(i)==m_target)
								alive.remove(i);
						}
						dead.add(m_target);
						nop--;
						sumop--;
						red=thp/mafia_no;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						thp=0;
					}
					else 
					{
						red=thp/mafia_no;
						thp=thp-som;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
				    }
	
				}
				else if(h_target==m_target)
				{
					
						red=thp/mafia_no;
						thp=500;
						
				
		                System.out.println("No one died");
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						
				}
                if(m_target>N/5 && m_target<=(2*N/5))
					d_player.get(m_target-1-N/5).hp=thp;
				else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
					h_player.get(m_target-1-(2*N/5)).hp=thp;
				else
				   c_player.get(m_target-1-healer_no-(2*N/5)).hp=thp;
                
				System.out.println("Choose a player to vote");
				u_vote=an.nextInt();
				
				val1=votes_gen(u_vote,nop,N,dead);
				System.out.println("Player"+val1+" has been voted out");
				dead.add(val1);
				
				
		
					for(i=0;i<alive.size();i++)
					{
						if(alive.get(i)==val1)
						{
							alive.remove(i);
						    nop--;
						    if(val1>=1 && val1<=N/5)
							{
								mafia_no=mafia_no-1;
								m_player.get(val1-1).hp=0;
							}
							else
							  sumop--;
						}
					}

				}
			if(mafia_no<=0)
			{
				System.out.println("Mafia has lost");
				System.out.println("END OF GAME");
			}
			else if(mafia_no==sumop)
			{
				System.out.println("Mafia Wins");
			    System.out.println("END OF GAME");
			}
			break;
			

		case 3:
			j=0;
			System.out.println("You are a Healer");
			System.out.println("You are player" + h_player.get(j).id);
			while(mafia_no!=sumop && mafia_no>0)
			{
				int val=-1;
				round++;
				System.out.println("ROUND"+round+":");
				System.out.print(nop+" Players are remaining:");
				
				
				for(i=0;i<alive.size();i++)
				{
				System.out.print("Player"+alive.get(i)+",");
				}
				System.out.println("\n Mafia has chosen a target");
				m_target=random_gen(N,dead);
				while(m_target>=1&&m_target<=N/5)
				{
				  m_target=random_gen(N,dead);
				}
				System.out.println("Detectives has choosen a player to test");
				d_test=random_gen(N,dead);
				System.out.println("Choose a player to heal");
				h_target=an.nextInt();
				System.out.println("...End of actions...");
				

	                if(m_target>N/5 && m_target<=(2*N/5))
						thp=d_player.get(m_target-1-N/5).hp;
					else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
						thp=h_player.get(m_target-1-(2*N/5)).hp;
					else
						thp=c_player.get(m_target-1-healer_no-(2*N/5)).hp;
				for(i=0;i<N/5;i++)
				{
					som=m_player.get(i).hp+som;
					if(m_player.get(i).id==d_test)
						val=i+1;
				}
				
				if(val!=-1)
					System.out.println("Player"+ val+" is mafia");
				else
					System.out.println("player"+d_test +"is not mafia");
                if(h_target!=m_target)
				{
					if(som>=thp)
					{
						System.out.println("Player "+m_target+" died");
						for(i=0;i<alive.size();i++)
						{
							if(alive.get(i)==m_target)
								alive.remove(i);
						}
						dead.add(m_target);
						nop--;
						sumop--;
						red=thp/mafia_no;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						thp=0;
					}
					else 
					{
						red=thp/mafia_no;
						thp=thp-som;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
				    }
	
				}
				else if(h_target==m_target)
				{
					
						red=thp/mafia_no;
						thp=500;
						
				
		                System.out.println("No one died");
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						
				}
                if(m_target>N/5 && m_target<=(2*N/5))
					d_player.get(m_target-1-N/5).hp=thp;
				else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
					h_player.get(m_target-1-(2*N/5)).hp=thp;
				else
				   c_player.get(m_target-1-healer_no-(2*N/5)).hp=thp;
                
				System.out.println("Choose a player to vote");
				u_vote=an.nextInt();
				
				val1=votes_gen(u_vote,nop,N,dead);
				System.out.println("Player"+val1+" has been voted out");
				dead.add(val1);
				
			
		
					for(i=0;i<alive.size();i++)
					{
						if(alive.get(i)==val1)
						{
							alive.remove(i);
						    nop--;
						    if(val1>=1 && val1<=N/5)
							{
								mafia_no=mafia_no-1;
								m_player.get(val1-1).hp=0;
							}
							else
							  sumop--;
						}
					}
				
				
				}
			if(mafia_no<=0)
			{
				System.out.println("Mafia has lost");
				 System.out.println("END OF GAME");
			}
			else if(mafia_no==sumop)
			{
				System.out.println("Mafia Wins");
			System.out.println("END OF GAME");
			}
			break;
		case 4:
			j=0;
			System.out.println("You are a Commoner");
			System.out.println("You are player" + c_player.get(j).id);
			while(mafia_no!=sumop && mafia_no>0)
			{
				int val=-1;
				round++;
				System.out.println("ROUND"+round+":");
				System.out.print(nop+" Players are remaining:");
				
				
				for(i=0;i<alive.size();i++)
				{
				System.out.print("Player"+alive.get(i)+",");
				}
				System.out.println("\n Mafia has chosen a target");
				m_target=random_gen(N,dead);
				while(m_target>=1&&m_target<=N/5)
				{
				  m_target=random_gen(N,dead);
				}
				System.out.println("Detectives has choosen a player to test");
				d_test=random_gen(N,dead);
				System.out.println("Healer has chosen a player to heal");
				h_target=random_gen(N,dead);
				System.out.println("...End of actions...");
				

	                if(m_target>N/5 && m_target<=(2*N/5))
						thp=d_player.get(m_target-1-N/5).hp;
					else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
						thp=h_player.get(m_target-1-(2*N/5)).hp;
					else
						thp=c_player.get(m_target-1-healer_no-(2*N/5)).hp;
				for(i=0;i<N/5;i++)
				{
					som=m_player.get(i).hp+som;
					if(m_player.get(i).id==d_test)
						val=i+1;
				}
				
				if(val!=-1)
					System.out.println("Player"+ val+" is mafia");
				else
					System.out.println("player"+d_test +"is not mafia");
                if(h_target!=m_target)
				{
					if(som>=thp)
					{
						System.out.println("Player "+m_target+" died");
						for(i=0;i<alive.size();i++)
						{
							if(alive.get(i)==m_target)
								alive.remove(i);
						}
						dead.add(m_target);
						nop--;
						sumop--;
						red=thp/mafia_no;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						thp=0;
					}
					else 
					{
						red=thp/mafia_no;
						thp=thp-som;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
				    }
	
				}
				else if(h_target==m_target)
				{
					
						red=thp/mafia_no;
						thp=500;
						
				
		                System.out.println("No one died");
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						
				}
                if(m_target>N/5 && m_target<=(2*N/5))
					d_player.get(m_target-1-N/5).hp=thp;
				else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
					h_player.get(m_target-1-(2*N/5)).hp=thp;
				else
				   c_player.get(m_target-1-healer_no-(2*N/5)).hp=thp;
                
				System.out.println("Choose a player to vote");
				u_vote=an.nextInt();
				
				val1=votes_gen(u_vote,nop,N,dead);
				System.out.println("Player"+val1+" has been voted out");
				dead.add(val1);
				
			
		
					for(i=0;i<alive.size();i++)
					{
						if(alive.get(i)==val1)
						{
							alive.remove(i);
						    nop--;
						    if(val1>=1 && val1<=N/5)
							{
								mafia_no=mafia_no-1;
								m_player.get(val1-1).hp=0;
							}
							else
							  sumop--;
						}
					}
					System.out.println("sum =" +sumop);
				
				}
			if(mafia_no<=0)
			{
				System.out.println("Mafia has lost");
                                  System.out.println("END GAME");
			}
				else if(mafia_no==sumop)
				{
					System.out.println("Mafia Wins");
				    System.out.println("END OF GAME");
				}
			break;
			



		case 5:
			j=random_gen(N,dead);
                        if(j>=1&&j<=N/5)
                        {
			                 System.out.println("You are a mafia");
                                p=1;
                        }
                        else if(j>N/5&&j<=(2*N)/5)
                        {       System.out.println("You are a Detective");
                                p=2;
                        }
                        else if(j>(2*N)/5 && j<=((2*N)/5)+healer_no)
                        {       
                        	System.out.println("You are a Healer");
                                p=3;
                        }
                        else
                        {
                               System.out.println("You are commoner");
                               p=4;
                        }
			System.out.println("You are player" + m_player.get(j).id);
                        if(p==1)
                        {
			            System.out.println("Other mafia are");
		
			
			         for(j=1;j<m_player.size();j++)
			         {
				          System.out.println("player"  + m_player.get(j).id);
			         }
                         }                       
			while(mafia_no!=sumop && mafia_no>0)
			{
				int val=-1;
				round++;
				System.out.println("ROUND"+round+":");
				System.out.print(nop+" Players are remaining:");
				
				
				for(i=0;i<alive.size();i++)
				{
				System.out.print("Player"+alive.get(i)+",");
				}
                                if(p==1)
                                {
				          System.out.println("\nChoose a target");
				          m_target=an.nextInt();
			    
   				          if(m_target>=1&&m_target<=N/5)
				          {
				                 System.out.println("you cannot target mafia");
				                 System.out.println("choose again");
				                 m_target=an.nextInt();
				          }
       				          System.out.println("Detectives has choosen a player to test");
				          d_test=random_gen(N,dead);
				          System.out.println("Healer has choosen a player to heal");
				          h_target=random_gen(N,dead);
				          System.out.println("...End of actions...");

                                }
                                else if(p==2)
                                {
				          System.out.println("\nMafia has chosen a player target");
				          m_target=random_gen(N,dead);
                                          while(m_target>=1&&m_target<=N/5)
                                          {
                                                  m_target=random_gen(N,dead);
                                          }
			    
   				          
       				          System.out.println("Choose a player to test");
				          d_test=an.nextInt();
				          System.out.println("Healer has choosen a player to heal");
				          h_target=random_gen(N,dead);
				          System.out.println("...End of actions...");

                                }
                                else if(p==3)
                                {
				          System.out.println("\n Mafia has chosen a target");
				          m_target=random_gen(N,dead);
			    
   				          while(m_target>=1&&m_target<=N/5)
				          {
				                 m_target=random_gen(N,dead);
				          }
       				          System.out.println("Detectives has choosen a player to test");
				              d_test=random_gen(N,dead);
				              System.out.println("Choose  a player to heal");
				              h_target=an.nextInt();
				                System.out.println("...End of actions...");

                               }
                                else
                                {
				                  System.out.println("\nMafia has chosen a target");
				                  m_target=random_gen(N,dead);
			    
   				          while(m_target>=1&&m_target<=N/5)
				          {
				                 m_target=random_gen(N,dead);
				          }
       				          System.out.println("Detectives has choosen a player to test");
				          d_test=random_gen(N,dead);
				          System.out.println("Healer has choosen a player to heal");
				          h_target=random_gen(N,dead);
				          System.out.println("...End of actions...");

                             }
                                if(m_target>N/5 && m_target<=(2*N/5))
						thp=d_player.get(m_target-1-N/5).hp;
					else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
						thp=h_player.get(m_target-1-(2*N/5)).hp;
					else
						thp=c_player.get(m_target-1-healer_no-(2*N/5)).hp;
				for(i=0;i<N/5;i++)
				{
					som=m_player.get(i).hp+som;
					if(m_player.get(i).id==d_test)
						val=i+1;
				}
				
				if(val!=-1)
					System.out.println("Player"+ val+" is mafia");
				else
					System.out.println("player"+d_test +"is not mafia");
                if(h_target!=m_target)
				{
					if(som>=thp)
					{
						System.out.println("Player "+m_target+" died");
						for(i=0;i<alive.size();i++)
						{
							if(alive.get(i)==m_target)
								alive.remove(i);
						}
						dead.add(m_target);
						nop--;
						sumop--;
						red=thp/mafia_no;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						thp=0;
					}
					else 
					{
						red=thp/mafia_no;
						thp=thp-som;
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
				    }
	
				}
				else if(h_target==m_target)
				{
					
						red=thp/mafia_no;
						thp=500;
						
				
		                System.out.println("No one died");
						for(i=0;i<mafia_no;i++)
						{
							if(m_player.get(i).hp<red)
							{
								rem=red- m_player.get(i).hp;
								m_player.get(i).hp=0;
								red=red+rem/mafia_no;
								
							}
							else 
								m_player.get(i).hp=m_player.get(i).hp-red;
						}
						
				}
                if(m_target>N/5 && m_target<=(2*N/5))
					d_player.get(m_target-1-N/5).hp=thp;
				else if(m_target>(2*N/5) && m_target<=(2*N/5+healer_no))
					h_player.get(m_target-1-(2*N/5)).hp=thp;
				else
				   c_player.get(m_target-1-healer_no-(2*N/5)).hp=thp;
                
				System.out.println("Choose a player to vote");
				u_vote=an.nextInt();
				
				val1=votes_gen(u_vote,nop,N,dead);
				System.out.println("Player"+val1+" has been voted out");
				dead.add(val1);
				
			
					for(i=0;i<alive.size();i++)
					{
						if(alive.get(i)==val1)
						{
							alive.remove(i);
						    nop--;
							if(val1>=1 && val1<=N/5)
							{
								mafia_no=mafia_no-1;
								m_player.get(val1-1).hp=0;
							}
							else
							  sumop--;
					
						}
					}
				
				}
			if(mafia_no<=0)
			{
				System.out.println("Mafia has lost");
				 System.out.println("END OF GAME");
			}
			else if(mafia_no==sumop)
			{
				System.out.println("Mafia Wins");
			System.out.println("END OF GAME");
			}
			break;
            }
			

		

		
		an.close();
		
   }
	public static int random_gen(int r,ArrayList <Integer> dd)
	{
		Random rand=new Random();
		int temp=rand.nextInt(r)+1;
		while(dd.contains(temp))
		{
			temp=rand.nextInt(r)+1;
		}
		return temp;
		
	}
	public static int votes_gen(int v,int no,int r,ArrayList <Integer> dd)
	{
		int votes[];
		int k=0,max,i;
		votes=new int[r];
		for(i=0;i<r;i++)
		{
			votes[i]=0;
		}
		votes[v-1]++;
		Random rand=new Random();
		
		while(no>1)
		{
			int temp=rand.nextInt(r)+1;
			while(dd.contains(temp))
			{
				temp=rand.nextInt(r)+1;
			}
			v=temp;
			votes[v-1]++;
			no--;
		}
		
		
		for(i=0;i<r;i++)
		{
			max=votes[0];
			if(votes[i]>max)
			{
				max=votes[i];
				k=i;
			}
		}
		return k+1;
	 
 	}
}