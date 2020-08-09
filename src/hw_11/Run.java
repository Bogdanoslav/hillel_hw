package hw_11;

public class Run {
    public enum  Suits {
        DIAMONDS, HEARTS, SPADES, CLUBS, ROSES, BELLS, ACORNS, SHIELDS;
    }
    public static void main(String[] args) {
        //==== testing record ====
        Card[] cards = {new Card("8", "Diamonds"),
                        new Card("A", "Diamonds"),
                        new Card("6", "Spades"),
                        new Card("Q", "Spades"),
                        new Card("6", "Clubs"),
                        new Card("J", "Spades"),
        };

        Player player_1 = new Player(cards);
        Player player_2 = new Player(cards);
        Player player_3 = new Player(null);;

        System.out.println("player1.hashCode(): " + player_1.hashCode());
        System.out.println("player2.hashCode(): " + player_2.hashCode());
        System.out.println("player_1.equals(player_2) => " + player_1.equals(player_2));
        System.out.println(player_1.toString());
        System.out.println(player_1.hand()[0]);
        //===== end of testing record =====

        //==== text blocks ====
        String sql = """
                Select *
                FROM cards 
                WHERE suits LIKE 'Clubs'
                """;
        System.out.println("sql : " + sql);
        //==== end of text blocks ====

        //==== switch expression ====
        Suits suit = Suits.SHIELDS;
        var color = switch(suit){
            case DIAMONDS, HEARTS, SPADES, CLUBS -> "French suits";
            case ROSES, ACORNS, SHIELDS, BELLS  -> "Swiss-German suits";
            default -> "unknown suit";
        };
        System.out.println(color);
        //==== end of switch expression ====

        //==== instance of ====
        Object c = new Card("A", "Hearts");
        if(c instanceof Card card){
            System.out.println("Карта: " + card.toString());
        }
        //==== end of instance of ====

        //==== NPE ====
        System.out.println(player_3.hand()[0]);
        //==== end of NPE ====


    }
}
