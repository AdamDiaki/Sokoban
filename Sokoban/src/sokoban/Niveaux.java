package sokoban;

import java.util.ArrayList;

/**
 * Déclaration des niveaux de jeu
 */
public class Niveaux {

    static final int gameWidth = 16;
    static final int gameHeight = 19;

    private static ArrayList<Niveau> niveaux = new ArrayList<Niveau>(){{

        // niveaux de jeu provenant du livre Eloquent JavaScript de Marijn Haverbeke  (1e édition, chapitre 13)

        this.add(new Niveau(10, "######  ##### |#    #  #   # |# 0  #### 0 # |# 0 @    0  # |#  #######0 # |####   ### ###|       #     #|       #0    #|       # 0   #|      ## 0   #|      #*0 0  #|      ########"));
        this.add(new Niveau(20, "###########   |#    #    #   |#  00#00 @#   |#     0   #   |#    #    #   |## #########  |#  0 #     #  |# 00 #0 0 0#  |#  0     0 #  |# 000#0  0 ###|#    #  0 0 *#|##############"));
        this.add(new Niveau(16, "##########    |#@      *#    |#       ##    |####### ######| #           #| # 0 0 0 0 0 #|######## #####|#   0 0  0 0 #|#   0        #|##### ########| #  0 0 0   # | #     0    # | # 0 0   0 ## |####### ####  |#  0     #    |#        #    |#   ######    |#####         "));
        this.add(new Niveau(12, " ####         |## @########  |#          #  |# 0#####0# #  |#  #   # 0 #  |# 0 0    0##  |# 0  0  #  #  |# ####0 ## #  |#  0   0 # ## |# ###0#   0 ##|#   #  0# 0 *#|#  0      ####|#####  #  #   |    #######   "));
        this.add(new Niveau(18, "######    #####|#  #*##  ##   #|#     #### 0  #|# 00  #  #  0 #|##  00#   00 ##| #0  0   #0  # | # 00 #  #  0# | # 0 0#### 0 # | #       #  ## | #### 0  # ##  |    ### ## #   |     # 0   #   |     #@ #  #   |     #######   "));
    }};

    static Niveau getNiveau(int i) {
        return niveaux.get(i);
    }

    static int nombreNiveau() {
        return niveaux.size();
    }
    
    static void changeNiveau(int niveau, int nbrPierre,String save) {
    	niveaux.set(niveau,new Niveau(nbrPierre,save));
    }
}
