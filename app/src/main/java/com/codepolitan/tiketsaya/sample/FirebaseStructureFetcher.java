package com.codepolitan.tiketsaya.sample;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on octubre.
 * year 2024 .
 */
public class FirebaseStructureFetcher {
    //{"MyTickets":{"Asep Suparjo":{"Torri-601819584":{"date_wisata":"27 November, 2019","id_ticket":"Torri-601819584","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Gate, Hongkong","nama_wisata":"Torri","time_wisata":"10 AM to 12 PM"}},"Budi":{"Pisa-1511556029":{"date_wisata":"27 November, 2019","id_ticket":"Pisa-1511556029","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"},"Pisa1245948523":{"date_wisata":"27 November, 2019","id_ticket":"Pisa1245948523","jumlah_tiket":"2","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"}},"Chikal":{"1397144614":{"date_wisata":"","id_ticket":"1397144614","jumlah_tiket":"2","ketentuan":"","lokasi":"","nama_wisata":"","time_wisata":""}},"MaherZain":{"Borobudur-1147610288":{"date_wisata":"27 November, 2019","id_ticket":"Borobudur-1147610288","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Magelang, Indonesia","nama_wisata":"Borobudur","time_wisata":"10 AM to 12 PM"},"Monas-2138162555":{"date_wisata":"27 November, 2019","id_ticket":"Monas-2138162555","jumlah_tiket":"2","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Jakarta, Indonesia","nama_wisata":"Monas","time_wisata":"10 AM to 12 PM"},"Pagoda-1436998432":{"date_wisata":"27 November, 2019","id_ticket":"Pagoda-1436998432","jumlah_tiket":"2","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Sanghai, China","nama_wisata":"Pagoda","time_wisata":"10 AM to 12 PM"},"Pagoda-1499080162":{"date_wisata":"27 November, 2019","id_ticket":"Pagoda-1499080162","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Sanghai, China","nama_wisata":"Pagoda","time_wisata":"10 AM to 12 PM"},"Pisa-1012994957":{"date_wisata":"27 November, 2019","id_ticket":"Pisa-1012994957","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"},"Pisa-259603297":{"date_wisata":"27 November, 2019","id_ticket":"Pisa-259603297","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"},"Pisa2065879324":{"date_wisata":"27 November, 2019","id_ticket":"Pisa2065879324","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"},"Pisa66206605":{"date_wisata":"27 November, 2019","id_ticket":"Pisa66206605","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"},"Sphinx-1228577672":{"date_wisata":"27 November, 2019","id_ticket":"Sphinx-1228577672","jumlah_tiket":"2","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pyramid, Mesir","nama_wisata":"Sphinx","time_wisata":"10 AM to 12 PM"},"Sphinx-327270156":{"date_wisata":"27 November, 2019","id_ticket":"Sphinx-327270156","jumlah_tiket":"8","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pyramid, Mesir","nama_wisata":"Sphinx","time_wisata":"10 AM to 12 PM"},"Sphinx119175135":{"date_wisata":"27 November, 2019","id_ticket":"Sphinx119175135","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pyramid, Mesir","nama_wisata":"Sphinx","time_wisata":"10 AM to 12 PM"}},"Mochamad Yusuf ":{"Pisa1996493871":{"date_wisata":"27 November, 2019","id_ticket":"Pisa1996493871","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"}},"Zain":{"Pisa-1591579489":{"date_wisata":"27 November, 2019","id_ticket":"Pisa-1591579489","jumlah_tiket":"1","ketentuan":"Anak berumur 3 tahun wajib beli tiket.","lokasi":"Pisa, Italy","nama_wisata":"Pisa","time_wisata":"10 AM to 12 PM"},"Pisa-368488990":{"date_wisata":"27 November, 2019","id_ticket":"Pi

    public void fetchDatabaseStructure() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                printStructure(dataSnapshot, 0);

                JSONObject json = null;
                try {
                    json = buildJson(dataSnapshot);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("---------------------------");

                System.out.println(json.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Error fetching database structure: " + databaseError.getMessage());
            }
        });
    }
    private JSONObject buildJson(DataSnapshot dataSnapshot) throws JSONException {
        JSONObject json = new JSONObject();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            if (child.hasChildren()) {
                json.put(child.getKey(), buildJson(child));
            } else {
                json.put(child.getKey(), child.getValue());
            }
        }
        return json;
    }
    private void printStructure(DataSnapshot dataSnapshot, int level) {
//        for (DataSnapshot child : dataSnapshot.getChildren()) {
//            printIndent(level);
//            System.out.println(child.getKey());
//            printStructure(child, level + 1);
//        }

        for (DataSnapshot child : dataSnapshot.getChildren()) {
            printIndent(level);
            System.out.print(child.getKey() + ": ");
            if (child.getValue() instanceof String || child.getValue() instanceof Number || child.getValue() instanceof Boolean) {
                System.out.println(child.getValue());
            } else {
                System.out.println();
                printStructure(child, level + 1);
            }
        }
    }

    private void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }


}