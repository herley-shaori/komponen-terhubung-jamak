package pengolahan.citra.herley;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class KomponenTerhubungJamak {

    public static void main(String[] args) {

        /**
         * Inisialisasi Point.
         */
        final Set<Point> set = new HashSet();
        final Point pointSatu = new Point(0, 1);
        final Point pointDua = new Point(1, 0);
        final Point pointTiga = new Point(1, 2);
        final Point pointEmpat = new Point(2, 1);
        final Point pointLima = new Point(1, 5);
        final Point pointEnam = new Point(1, 6);
        final Point pointTujuh = new Point(1, 7);
        final Point pointDelapan = new Point(4, 1);

        /**
         * Tambahkan pada himpunan titik.
         */
        set.add(pointSatu);
        set.add(pointDua);
        set.add(pointTiga);
        set.add(pointEmpat);
        set.add(pointLima);
        set.add(pointEnam);
        set.add(pointTujuh);
        set.add(pointDelapan);

        /**
         * Struktur Data untuk Queue menggunakan BFS.
         */
        final LinkedHashSet<Point> linkedHashSet = new LinkedHashSet();

        Iterator<Point> conturIterator = set.iterator();
        Point titikAwal = conturIterator.next();
        linkedHashSet.add(titikAwal);

        /**
         * List Connected Components.
         */
        final List<List<Point>> connectedComponentsList = new ArrayList();
        
        /**
         * Awal BFS. Selama himpunan titik (set) tidak kosong.
         */
        while (!set.isEmpty()) {

            /**
             * listPoint menyimpan jumlah titik dalam satu connected components.
             */
            final List<Point> listPoint = new ArrayList();
            
            if(linkedHashSet.isEmpty()){
                linkedHashSet.add(set.iterator().next());
            }
            
            /**
             * Selama antrian tidak kosong, cari connected components.
             */
            while (!linkedHashSet.isEmpty()) {
                Iterator<Point> iterSatu = linkedHashSet.iterator();
                /**
                 * Ambil Satu titik sembarang.
                 */
                Point titikSatu = iterSatu.next();
                /**
                 * Cari Tetangganya, dan hapus. Hapus dari LinkedHashSet dan
                 * hapus juga dari SetPoint kontur.
                 *
                 */
                conturIterator = set.iterator();
                /**
                 * Bandingkan jarak (titikSatu) dengan seluruh titik pada himpunan titik.
                 */
                while (conturIterator.hasNext()) {
                    Point titikDua = conturIterator.next();
                    /**
                     * Jika jaraknya sama dengan 1 (menggunakan Euclidean Distance), maka titik ini (titikDua) adalah tetangga dari titikSatu.
                     */
                    if (Math.toIntExact(Math.round(titikSatu.distance(titikDua))) == 1) {
                        /**
                         * Tambahkan tetangga ini ke antrian.
                         */
                        linkedHashSet.add(titikDua);
                    }
                }

                /**
                 * 1. Tambahkan titikSatu pada himpunan satu objek connected components.
                 * 2. Hapus titikSatu dari himpunan titik.
                 * 3. Hapus titikSatu dari antrian tetangga.
                 */
                listPoint.add(titikSatu);
                set.remove(titikSatu);
                linkedHashSet.remove(titikSatu);
                /**
                 * BFS selesai, deque titik tetangga yang tadi di-enque.
                 */
            }
            /**
             * Periksa apakah himpunan titik kosong. Jika kosong, hentikan loop, jika tidak, lanjutkan hingga kosong.
             */
            connectedComponentsList.add(listPoint);
        }
        
        System.out.println("Jumlah Connected Components: "+connectedComponentsList.size());
        for (int i = 0; i < connectedComponentsList.size(); i++) {
            System.out.println("Komponen ke: "+(i+1));
            System.out.println("Himpunan Titik: "+connectedComponentsList.get(i));
            System.out.println();
        }
    }
}