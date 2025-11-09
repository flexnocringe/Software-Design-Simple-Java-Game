import java.util.*;

public class PathFinder {
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static List<int[]> findShortestPath(GameMap map, Citizen citizen) {
        int height = map.getMapHeight();
        int width = map.getMapWidth();

        boolean[][] visited = new boolean[height][width];

        Map<Integer, Integer> parent = new HashMap<>();

        Queue<int[]> queue = new LinkedList<>();
        int startX = citizen.getX();
        int startY = citizen.getY();

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        Integer endKey = null;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (map.getTile(x, y) == 'S') {
                endKey = encode(x, y, width);
                break;
            }

            for (int[] d : DIRECTIONS) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx >= 1 && nx < height && ny >= 1 && ny < width && !visited[nx][ny]) {
                    char tile = map.getTile(nx, ny);
                    if (tile == 'R' || tile == 'S' || tile == 'C') {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        int childKey = encode(nx, ny, width);
                        parent.put(childKey, encode(x, y, width));
                    }
                }
            }
        }

        if (endKey == null) {
            return Collections.emptyList();
        }

        List<int[]> path = new ArrayList<>();
        Integer key = endKey;
        while (key != null) {
            int[] coord = decode(key, width);
            path.add(coord);
            key = parent.get(key);
        }
        Collections.reverse(path);
        path.removeFirst();
        return path;
    }

    private static int encode(int x, int y, int width) {
        return x * width + y;
    }

    private static int[] decode(int key, int width) {
        int x = key / width;
        int y = key % width;
        return new int[]{x, y};
    }
}