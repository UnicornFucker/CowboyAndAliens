package de.comyoutech.cowboyandalien.model;

import java.util.List;

import de.comyoutech.cowboyandalien.entities.AbstractEntity;
import de.comyoutech.cowboyandalien.entities.BlockEntity;
import de.comyoutech.cowboyandalien.entities.CactusEntity;
import de.comyoutech.cowboyandalien.entities.CoinEntity;
import de.comyoutech.cowboyandalien.entities.EnemyEntity;
import de.comyoutech.cowboyandalien.entities.GateEntity;
import de.comyoutech.cowboyandalien.entities.MovableBlockEntity;
import de.comyoutech.cowboyandalien.entities.PlayerEntity;
import de.comyoutech.cowboyandalien.entities.SpikeEntity;

/**
 * Generates several levels.
 * 
 * @author Felix
 * 
 */
public class EntityGenerator {

    /** This Method decides which level should be loaded by input **/

    public static void generateLevelIn(List<AbstractEntity> blockList) {

        generateLevel1(blockList);

        // for (int i = 0; i < 10; i++) {
        // blockList.add(new BlockEntity(i, 0));
        // blockList.add(new BlockEntity(i, 6));
        // if (i > 2) {
        // blockList.add(new BlockEntity(i, 1));
        // }
        // blockList.add(new BlockEntity(9, 2));
        // blockList.add(new BlockEntity(9, 3));
        // blockList.add(new BlockEntity(9, 4));
        // blockList.add(new BlockEntity(9, 5));
        //
        // blockList.add(new BlockEntity(6, 3));
        // blockList.add(new BlockEntity(6, 4));
        // blockList.add(new BlockEntity(6, 5));
        // }
        // generateTestLvl1(blockList);
    }

    /** List for Level 2 **/

    public static void generateTestLvl2(List<AbstractEntity> blockList) {

        blockList.add(new CoinEntity(5, 0, true));

        // blockList.add(new EnemyEntity(5, 0));
        // blockList.add(new BlockEntity(3, 0));
        // blockList.add(new BlockEntity(7, 0));
        // blockList.add(new BlockEntity(10, 0));
        // blockList.add(new BlockEntity(11, 0));
        // blockList.add(new BlockEntity(11, 1));
        // blockList.add(new MovableBlockEntity(5, 5, false));
        // blockList.add(new BlockEntity(14, 0));
        // blockList.add(new BlockEntity(15, 0));
        // blockList.add(new BlockEntity(15, 1));
        // blockList.add(new BlockEntity(16, 0));
        // blockList.add(new BlockEntity(16, 1));
        // blockList.add(new BlockEntity(16, 2));
        // blockList.add(new BlockEntity(17, 2));
        // blockList.add(new BlockEntity(18, 2));
        // blockList.add(new BlockEntity(19, 2));
        // blockList.add(new BlockEntity(20, 2));
        // blockList.add(new BlockEntity(20, 3));
        // blockList.add(new BlockEntity(21, 4));
        // blockList.add(new BlockEntity(21, 2));
        // blockList.add(new BlockEntity(11, 1));
        // blockList.add(new BlockEntity(14, 0));
        // blockList.add(new BlockEntity(15, 0));
        // blockList.add(new BlockEntity(15, 1));
        // blockList.add(new BlockEntity(16, 0));
        // blockList.add(new BlockEntity(16, 1));
        // blockList.add(new BlockEntity(26, 2));
        // blockList.add(new BlockEntity(27, 2));
        // blockList.add(new BlockEntity(28, 2));
        // blockList.add(new BlockEntity(29, 2));
        // blockList.add(new BlockEntity(30, 2));
        // blockList.add(new BlockEntity(30, 3));
        // blockList.add(new BlockEntity(31, 4));
        // blockList.add(new BlockEntity(31, 2));
        //
        // blockList.add(new CoinEntity(21, 5));
        // blockList.add(new CoinEntity(13, 2));
        // blockList.add(new CoinEntity(16, 0));
        // blockList.add(new CoinEntity(12, 0));
    }

    /** generate a new Entity of Player **/

    public static PlayerEntity generatePlayer() {
        return new PlayerEntity(6, 3);
    }

    /** generate a Random List for Level 1 **/

    private static void generateLevelPresi(List<AbstractEntity> bl) {
        // BODEN
        bVN(0, 23, 0, bl);
        bVN(23, 27, 0, bl, true);
        bVN(27, 29, 0, bl);
        bVN(14, 22, 1, bl);
        bVN(14, 22, 2, bl);
        bVN(17, 19, 5, bl);

        sVN(29, 36, 0, bl);

        bVN(36, 40, 0, bl);
        bVN(40, 53, 0, bl, true);
        bVN(53, 80, 0, bl);
        bVN(40, 53, 4, bl);

        bl.add(new BlockEntity(0, 0));
        bl.add(new BlockEntity(0, 1));
        bl.add(new BlockEntity(0, 2));
        bl.add(new BlockEntity(0, 2));
        bl.add(new BlockEntity(1, 2));
        bl.add(new BlockEntity(1, 1));
        bl.add(new BlockEntity(2, 1));
        bl.add(new BlockEntity(13, 1));
        bl.add(new BlockEntity(13, 2));
        bl.add(new BlockEntity(14, 3));
        bl.add(new BlockEntity(21, 3));
        bl.add(new BlockEntity(28, 2));
        bl.add(new BlockEntity(31, 4));
        bl.add(new BlockEntity(39, 2));
        bl.add(new BlockEntity(87, 0));

        bl.add(new EnemyEntity(14, 3));
        bl.add(new EnemyEntity(83, 0, true));

        bl.add(new GateEntity(87, 3));

        bl.add(new CactusEntity(1, 3));
        bl.add(new CactusEntity(2, 2));
        bl.add(new CactusEntity(3, 1));
        bl.add(new CactusEntity(9, 1));

        bl.add(new CoinEntity(17, 6));
        bl.add(new CoinEntity(18, 6));
        bl.add(new CoinEntity(41, 1));
        bl.add(new CoinEntity(44, 1));
        bl.add(new CoinEntity(47, 1));
        bl.add(new CoinEntity(50, 1));
        bl.add(new CoinEntity(0, 3, true));
        bl.add(new CoinEntity(35, 5, true));

        bl.add(new MovableBlockEntity(40, 2, 2, true));
        bl.add(new MovableBlockEntity(43, 2, 2, true));
        bl.add(new MovableBlockEntity(46, 2, 2, true));
        bl.add(new MovableBlockEntity(49, 2, 2, true));
    }

    private static void bVN(float a, float b, float y, List<AbstractEntity> bl) {
        for (float i = a; i != b; i++) {
            bl.add(new BlockEntity(i, y));
        }
    }

    private static void sVN(float a, float b, float y, List<AbstractEntity> bl) {
        for (float i = a; i != b; i++) {
            bl.add(new SpikeEntity(i, y));
        }
    }

    private static void bVN(float a, float b, float y, List<AbstractEntity> bl,
            boolean ice) {
        for (float i = a; i != b; i++) {
            bl.add(new BlockEntity(i, y, ice));
        }
    }

    private static void generateLevel1(List<AbstractEntity> blockList) {
        blockList.add(new BlockEntity(1, 0));
        blockList.add(new BlockEntity(2, 0));
        blockList.add(new BlockEntity(3, 0));
        blockList.add(new BlockEntity(4, 0));
        blockList.add(new BlockEntity(5, 0));
        blockList.add(new BlockEntity(6, 0, true));
        blockList.add(new BlockEntity(7, 0, true));
        blockList.add(new BlockEntity(8, 0));
        blockList.add(new BlockEntity(9, 0));
        blockList.add(new CactusEntity(9, 1));
        blockList.add(new BlockEntity(10, 0));
        blockList.add(new BlockEntity(11, 0));
        blockList.add(new BlockEntity(12, 0));
        blockList.add(new BlockEntity(13, 0));
        blockList.add(new BlockEntity(14, 0));

        blockList.add(new BlockEntity(10, 1));
        blockList.add(new BlockEntity(10, 2));
        blockList.add(new BlockEntity(11, 0));
        blockList.add(new BlockEntity(12, 0));
        blockList.add(new BlockEntity(12, 4));
        blockList.add(new BlockEntity(13, 0));
        blockList.add(new BlockEntity(13, 4));
        blockList.add(new BlockEntity(14, 0));
        blockList.add(new BlockEntity(15, 0));
        blockList.add(new BlockEntity(15, 2));
        blockList.add(new BlockEntity(16, 0));
        blockList.add(new BlockEntity(16, 2));
        blockList.add(new BlockEntity(17, 0));
        blockList.add(new BlockEntity(18, 0));
        blockList.add(new BlockEntity(19, 0));
        blockList.add(new BlockEntity(20, 1));
        blockList.add(new BlockEntity(21, 2));
        blockList.add(new BlockEntity(22, 3));
        blockList.add(new BlockEntity(23, 4));
        blockList.add(new BlockEntity(24, 0));
        blockList.add(new BlockEntity(25, 0));
        blockList.add(new BlockEntity(25, 2));
        blockList.add(new BlockEntity(26, 0));
        blockList.add(new BlockEntity(26, 2));
        blockList.add(new BlockEntity(26, 3));
        blockList.add(new BlockEntity(26, 4));
        blockList.add(new BlockEntity(26, 5));
        blockList.add(new BlockEntity(27, 1));
        blockList.add(new BlockEntity(28, 1));
        blockList.add(new BlockEntity(28, 3));
        blockList.add(new BlockEntity(28, 4));
        blockList.add(new BlockEntity(28, 5));
        blockList.add(new BlockEntity(28, 6));
        blockList.add(new BlockEntity(28, 7));
        blockList.add(new BlockEntity(29, 1));
        blockList.add(new BlockEntity(29, 5));
        blockList.add(new BlockEntity(29, 6));
        blockList.add(new BlockEntity(30, 1));
        blockList.add(new BlockEntity(30, 3));
        blockList.add(new BlockEntity(30, 6));
        blockList.add(new BlockEntity(31, 1));
        blockList.add(new BlockEntity(31, 2));
        blockList.add(new BlockEntity(31, 4));
        blockList.add(new BlockEntity(33, 4));
        blockList.add(new BlockEntity(34, 4));
        blockList.add(new BlockEntity(37, 2));
        blockList.add(new BlockEntity(39, 3));
        blockList.add(new BlockEntity(42, 4));
        blockList.add(new BlockEntity(44, 5));
        blockList.add(new BlockEntity(49, 0));
        blockList.add(new BlockEntity(49, 1));
        blockList.add(new BlockEntity(50, 0, true));
        blockList.add(new BlockEntity(51, 0, true));
        blockList.add(new BlockEntity(52, 0, true));
        blockList.add(new BlockEntity(53, 0, true));
        blockList.add(new BlockEntity(54, 0, true));
        blockList.add(new BlockEntity(55, 0, true));
        blockList.add(new BlockEntity(56, 0, true));
        blockList.add(new BlockEntity(56, 1));
        blockList.add(new BlockEntity(56, 2));
        blockList.add(new BlockEntity(57, 0, true));
        blockList.add(new BlockEntity(57, 2));
        blockList.add(new BlockEntity(57, 3));
        blockList.add(new BlockEntity(57, 4));
        blockList.add(new BlockEntity(58, 0, true));
        blockList.add(new BlockEntity(58, 4));
        blockList.add(new BlockEntity(59, 0, true));
        blockList.add(new BlockEntity(59, 2));
        blockList.add(new BlockEntity(59, 4));
        blockList.add(new BlockEntity(60, 0, true));
        blockList.add(new BlockEntity(61, 0, true));
        blockList.add(new BlockEntity(61, 4));
        blockList.add(new BlockEntity(61, 5));
        blockList.add(new BlockEntity(61, 6));
        blockList.add(new BlockEntity(62, 0, true));
        blockList.add(new BlockEntity(62, 2));
        blockList.add(new BlockEntity(62, 4));
        blockList.add(new BlockEntity(62, 5));
        blockList.add(new BlockEntity(62, 6));
        blockList.add(new BlockEntity(63, 2));
        blockList.add(new BlockEntity(63, 4));
        blockList.add(new BlockEntity(63, 5));
        blockList.add(new BlockEntity(63, 6));
        blockList.add(new BlockEntity(64, 2));
        blockList.add(new BlockEntity(64, 4));
        blockList.add(new BlockEntity(64, 5));
        blockList.add(new BlockEntity(64, 6));
        blockList.add(new BlockEntity(65, 2));
        blockList.add(new BlockEntity(65, 4));
        blockList.add(new BlockEntity(65, 5));
        blockList.add(new BlockEntity(65, 6));
        blockList.add(new BlockEntity(66, 2));
        blockList.add(new BlockEntity(66, 4));
        blockList.add(new BlockEntity(66, 5));
        blockList.add(new BlockEntity(66, 6));
        blockList.add(new BlockEntity(67, 2));
        blockList.add(new BlockEntity(67, 4));
        blockList.add(new BlockEntity(67, 5));
        blockList.add(new BlockEntity(67, 6));
        blockList.add(new BlockEntity(68, 2));
        blockList.add(new BlockEntity(68, 4));
        blockList.add(new BlockEntity(68, 5));
        blockList.add(new BlockEntity(69, 2));
        blockList.add(new BlockEntity(69, 4));
        blockList.add(new BlockEntity(69, 5));
        blockList.add(new BlockEntity(69, 6));
        blockList.add(new BlockEntity(70, 2));
        blockList.add(new BlockEntity(70, 6));
        blockList.add(new BlockEntity(71, 2));
        blockList.add(new BlockEntity(71, 3));
        blockList.add(new BlockEntity(71, 6));
        blockList.add(new BlockEntity(72, 0));
        blockList.add(new BlockEntity(72, 1));
        blockList.add(new BlockEntity(72, 2));
        blockList.add(new BlockEntity(72, 3));
        blockList.add(new BlockEntity(72, 6));
        blockList.add(new BlockEntity(73, 0));
        blockList.add(new BlockEntity(73, 6));
        blockList.add(new BlockEntity(74, 0));
        blockList.add(new BlockEntity(74, 2));
        blockList.add(new BlockEntity(74, 3));
        blockList.add(new BlockEntity(74, 4));
        blockList.add(new BlockEntity(74, 5));
        blockList.add(new BlockEntity(74, 6));
        blockList.add(new BlockEntity(75, 0));
        blockList.add(new BlockEntity(75, 2));
        blockList.add(new BlockEntity(75, 6));
        blockList.add(new BlockEntity(76, 0));
        blockList.add(new BlockEntity(76, 4));
        blockList.add(new BlockEntity(76, 6));
        blockList.add(new BlockEntity(77, 0));
        blockList.add(new BlockEntity(77, 1));
        blockList.add(new BlockEntity(77, 2));
        blockList.add(new BlockEntity(77, 3));
        blockList.add(new BlockEntity(77, 4));
        blockList.add(new BlockEntity(77, 6));
        blockList.add(new BlockEntity(78, 0));
        blockList.add(new BlockEntity(78, 1));
        blockList.add(new BlockEntity(78, 2));
        blockList.add(new BlockEntity(78, 3));
        blockList.add(new BlockEntity(78, 4));
        blockList.add(new BlockEntity(78, 6));
        blockList.add(new BlockEntity(79, 0));
        blockList.add(new BlockEntity(79, 1));
        blockList.add(new BlockEntity(79, 2));
        blockList.add(new BlockEntity(79, 3));
        blockList.add(new BlockEntity(79, 4));
        blockList.add(new BlockEntity(79, 6));
        blockList.add(new BlockEntity(80, 0));
        blockList.add(new BlockEntity(80, 6));
        blockList.add(new BlockEntity(81, 0));
        blockList.add(new BlockEntity(81, 2));
        blockList.add(new BlockEntity(81, 3));
        blockList.add(new BlockEntity(81, 4));
        blockList.add(new BlockEntity(81, 5));
        blockList.add(new BlockEntity(81, 6));
        blockList.add(new BlockEntity(82, 0));
        blockList.add(new BlockEntity(83, 0));
        blockList.add(new BlockEntity(84, 0));
        blockList.add(new BlockEntity(87, 1));
        blockList.add(new BlockEntity(90, 2));
        blockList.add(new BlockEntity(93, 3));
        blockList.add(new BlockEntity(96, 4));
        blockList.add(new BlockEntity(99, 5));
        blockList.add(new BlockEntity(104, 0));
        blockList.add(new BlockEntity(104, 1));
        blockList.add(new BlockEntity(105, 0));
        blockList.add(new BlockEntity(106, 0));
        blockList.add(new BlockEntity(107, 0));
        blockList.add(new BlockEntity(108, 0));
        blockList.add(new BlockEntity(109, 0));
        blockList.add(new BlockEntity(110, 0));
        blockList.add(new BlockEntity(111, 0));
        blockList.add(new BlockEntity(112, 0));
        blockList.add(new BlockEntity(113, 0));
        blockList.add(new BlockEntity(114, 0));
        blockList.add(new BlockEntity(115, 0));
        blockList.add(new BlockEntity(116, 0));
        blockList.add(new BlockEntity(116, 1));

        blockList.add(new SpikeEntity(72, 4));
        sVN(27, 49, 0, blockList);
        sVN(63, 71, 0, blockList);
        sVN(85, 104, 0, blockList);

        blockList.add(new GateEntity(111, 3));
        // endboss
        blockList.add(new EnemyEntity(108, 1, true));
        blockList.add(new CoinEntity(12, 1));
        blockList.add(new CoinEntity(12, 5));

        blockList.add(new CoinEntity(11, 1));
        blockList.add(new CoinEntity(13, 1));
        blockList.add(new CoinEntity(14, 1));
        blockList.add(new CoinEntity(15, 1));

        blockList.add(new CoinEntity(13, 5));
        blockList.add(new CoinEntity(14, 1));
        blockList.add(new CoinEntity(15, 3));
        blockList.add(new CoinEntity(16, 3));
        blockList.add(new CoinEntity(20, 2));
        blockList.add(new CoinEntity(21, 3));
        blockList.add(new CoinEntity(22, 4));
        blockList.add(new CoinEntity(23, 5));
        blockList.add(new CoinEntity(25, 1));
        blockList.add(new CoinEntity(26, 1));
        blockList.add(new CoinEntity(27, 2));
        blockList.add(new CoinEntity(27, 3));
        blockList.add(new CoinEntity(27, 4));
        blockList.add(new CoinEntity(27, 5));
        blockList.add(new CoinEntity(27, 6));
        blockList.add(new CoinEntity(28, 2));
        blockList.add(new CoinEntity(30, 5));
        blockList.add(new CoinEntity(31, 3));
        blockList.add(new CoinEntity(37, 3));
        blockList.add(new CoinEntity(39, 4));
        blockList.add(new CoinEntity(42, 5));
        blockList.add(new CoinEntity(44, 6));
        blockList.add(new CoinEntity(57, 1));
        blockList.add(new CoinEntity(58, 1));
        blockList.add(new CoinEntity(58, 2));
        blockList.add(new CoinEntity(58, 3));
        blockList.add(new CoinEntity(59, 1));
        blockList.add(new CoinEntity(59, 3));
        blockList.add(new CoinEntity(60, 1));
        blockList.add(new CoinEntity(60, 2));
        blockList.add(new CoinEntity(60, 3));
        blockList.add(new CoinEntity(61, 1));
        blockList.add(new CoinEntity(61, 2));
        blockList.add(new CoinEntity(61, 3));
        blockList.add(new CoinEntity(61, 7));
        blockList.add(new CoinEntity(68, 6));
        blockList.add(new CoinEntity(76, 5));
        blockList.add(new CoinEntity(77, 5));
        blockList.add(new CoinEntity(78, 5));
        blockList.add(new CoinEntity(79, 5));
        blockList.add(new CoinEntity(81, 1));
        blockList.add(new CoinEntity(87, 2));
        blockList.add(new CoinEntity(90, 3));
        blockList.add(new CoinEntity(93, 4));
        blockList.add(new CoinEntity(96, 5));
        blockList.add(new CoinEntity(99, 6));
        blockList.add(new EnemyEntity(12, 1));
        blockList.add(new EnemyEntity(14, 1));
        blockList.add(new BlockEntity(16, 1));
        blockList.add(new EnemyEntity(17, 1));
        // blockList.add(new EnemyEntity(28, 2));

        blockList.add(new EnemyEntity(51, 1));
        blockList.add(new EnemyEntity(53, 1));
        // blockList.add(new EnemyEntity(75, 1));
        // blockList.add(new MovableBlockEntity(19, 6, false));
        blockList.add(new MovableBlockEntity(20, 2, true));
        // blockList.add(new MovableBlockEntity(23, 6, false));
        // blockList.add(new MovableBlockEntity(35, 4, true));
        // blockList.add(new MovableBlockEntity(64, 3, false));
    }

    public static void generateTestLvl1(List<AbstractEntity> blockList) {
        // blockList.add(new BlockEntity(2, 0));
        // blockList.add(new BlockEntity(1, 0));
        // blockList.add(new MovableBlockEntity(5, 5, false));
        //
        // blockList.add(new BlockEntity(8, 0));
        // blockList.add(new BlockEntity(3, 0));
        // blockList.add(new BlockEntity(7, 0));
        // List<Integer> holes = new ArrayList<Integer>();
        // Random random = new Random();
        //
        // int width = 1000;
        //
        // for (int i = 0; i < 300; i++) {
        // int x = random.nextInt(width);
        // int y = random.nextInt(2);
        // for (int o = x; o < y; o++) {
        // holes.add(o);
        // }
        // holes.add(x);
        // }
        // for (int i = 5; i < width; i++) {
        // if (!holes.contains(i)) {
        // blockList.add(new BlockEntity(i, 0));
        // blockList.add(new CoinEntity(i - 5, 1));
        // }
        // else {
        // // TODO HoleEntity
        // }
        // }
        //
        // List<Integer> coins = new ArrayList<Integer>();
        // Random randomCoins = new Random();
        //
        // int widthCoins = 100;
        //
        // for (int i = 0; i < 30; i++) {
        // int x = random.nextInt(width);
        // int y = random.nextInt(2);
        // for (int o = x; o < y; o++) {
        // coins.add(o);
        // }
        // coins.add(x);
        // }
        // for (int i = 5; i < width; i++) {
        // if (!holes.contains(i)) {
        // blockList.add(new BlockEntity(i, 0));
        // }
        // else {
        // // TODO HoleEntity
        // }
        // }
        blockList.add(new CoinEntity(5, 0, true));
        blockList.add(new CoinEntity(7, 0));

    }
}
