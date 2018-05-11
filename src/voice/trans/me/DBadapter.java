package voice.trans.me;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBadapter extends SQLiteOpenHelper {

	public static final String TABLE_COMMENTS = "comments";
	 public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_COMMENT = "comment";
	
	private static final String DATABASE_NAME = "mydb";
	private static final int DATABASE_VERSION = 3;
	
	
	// buat tabel
	  private static final String DATABASE_CREATE = "create table "
		      + "ms_kamus" + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + "t_indo"
		      + " text not null,t_hira text not null,t_kata text not null,a_hira text not null,a_kata text not null);";	
	  
	  private static final String create_tbl_kunci = "create table "
		      + "ms_kunci" + "(" + COLUMN_ID
		      + " integer primary key autoincrement,tjml integer);";
	  
	  String sql="insert into ms_kamus (t_indo,t_hira,t_kata,a_hira,a_kata) values "
			  + "(UPPER('Selamat Pagi'),UPPER('Ohayo'),UPPER('Ohayo'),UPPER('おはよぅ'),UPPER('オハヨウ')),"
			  + "(UPPER('Selamat Siang'),UPPER('Konnichiwa'),UPPER('Konnichiwa'),UPPER('こんにちは'),UPPER('コンニチワ')),"
			  + "(UPPER('Selamat Malam'),UPPER('Konbanwa'),UPPER('Konbanwa'),UPPER('こんばんわ'),UPPER('コンバンワ')),"
			  + "(UPPER('Selamat Datang'),UPPER('Kangei'),UPPER('Kangei'),UPPER('かんげい'),UPPER('カンゲイ')),"
			  + "(UPPER('Selamat Tinggal'),UPPER('Sayōnara'),UPPER('Sayōnara'),UPPER('さようなら'),UPPER('サヨウナラ')),"
			  + "(UPPER('Selamat'),UPPER('Omedetōgozaimasu'),UPPER('Omedetōgozaimasu'),UPPER('おめでとうございます'),UPPER('オメデトウゴザイマス')),"
			  + "(UPPER('Saya'),UPPER('watashi'),UPPER('watashi'),UPPER('わたし'),UPPER('ワタシ')),"
			  + "(UPPER('Kami'),UPPER('Watashitachi'),UPPER('Watashitachi'),UPPER('わたしたち'),UPPER('ワタシタチ')),"
			  + "(UPPER('Kamu'),UPPER('Anata'),UPPER('Anata'),UPPER('あなた'),UPPER('アナタ')),"
			  + "(UPPER('Dia (laki)'),UPPER('Kare (osu)'),UPPER('Kare (osu)'),UPPER('かれ （おす）'),UPPER('カレ （オス）')),"
			  + "(UPPER('Dia (perempuan)'),UPPER('Kare (mesu)'),UPPER('Kare (mesu)'),UPPER('かれ （めす）'),UPPER('カレ （メス）')),"
			  + "(UPPER('Ini'),UPPER('Kono'),UPPER('Kono'),UPPER('この'),UPPER('コノ')),"
			  + "(UPPER('Itu'),UPPER('Sore'),UPPER('Sore'),UPPER('それ'),UPPER('ソレ')),"
			  + "(UPPER('Terimakasih'),UPPER('Kansha'),UPPER('Kansha'),UPPER('かんしゃ'),UPPER('カンシャ')),"
			  + "(UPPER('Iya'),UPPER('Hai'),UPPER('Hai'),UPPER('はい'),UPPER('ハイ')),"
			  + "(UPPER('Tidak'),UPPER('Shimasen'),UPPER('Shimasen'),UPPER('しません'),UPPER('シマセン')),"
			  + "(UPPER('Bekerja'),UPPER('Shigoto'),UPPER('Shigoto'),UPPER('しごと'),UPPER('シゴト')),"
			  + "(UPPER('Belajar'),UPPER('Manabimasu'),UPPER('Manabimasu'),UPPER('まなびます'),UPPER('マナビマス')),"
			  + "(UPPER('Berbicara'),UPPER('Hanashimasu'),UPPER('Hanashimasu'),UPPER('はなします'),UPPER('ハナシマス')),"
			  + "(UPPER('Berdoa'),UPPER('inorimasu'),UPPER('inorimasu'),UPPER('いのります'),UPPER('イノリマス')),"
			  + "(UPPER('0 (nol)'),UPPER('zero'),UPPER('zero'),UPPER('ぜろ'),UPPER('ゼロ')),"
			  + "(UPPER('1 (satu)'),UPPER('Ichi'),UPPER('Ichi'),UPPER('いち'),UPPER('イチ')),"
			  + "(UPPER('2 (dua)'),UPPER('Ni'),UPPER('Ni'),UPPER('に'),UPPER('ニ')),"
			  + "(UPPER('3 (tiga)'),UPPER('San'),UPPER('San'),UPPER('さん'),UPPER('サン')),"
			  + "(UPPER('4 (empat)'),UPPER('Shi/Yon'),UPPER(' Shi/Yon'),UPPER('し/よん'),UPPER('シ/ヨン')),"
			  + "(UPPER('5 (lima)'),UPPER('Go'),UPPER('Go'),UPPER('ご'),UPPER('ゴ')),"
			  + "(UPPER('6 (enam)'),UPPER('Roku'),UPPER('Roku'),UPPER('ろく'),UPPER('ロク')),"
			  + "(UPPER('7 (tujuh)'),UPPER('Nana'),UPPER('Nana'),UPPER('なな'),UPPER('ナナ')),"
			  + "(UPPER('8 (delapan)'),UPPER('Hachi'),UPPER('Hachi'),UPPER('はち'),UPPER('ハチ')),"
			  + "(UPPER('9 (sembilan)'),UPPER('Ku/Kyuu'),UPPER('Ku/Kyuu'),UPPER('く/きゅう'),UPPER('ク/キュウ')),"
			  + "(UPPER('10 (sepuluh)'),UPPER('Juu'),UPPER('Juu'),UPPER('じゅう'),UPPER('ジュウ')),"
			  + "(UPPER('Senin'),UPPER('Getsuyobi'),UPPER('Getsuyobi'),UPPER('げつよび'),UPPER('ゲツヨビ')),"
			  + "(UPPER('Selasa'),UPPER('Kayobi'),UPPER('Kayobi'),UPPER('かよび'),UPPER('カヨビ')),"
			  + "(UPPER('Rabu'),UPPER('Suiyobi'),UPPER('Suiyobi'),UPPER('すいよび'),UPPER('スイヨビ')),"
			  + "(UPPER('Kamis'),UPPER('Mokuyobi'),UPPER('Mokuyobi'),UPPER('もくよび'),UPPER('モクヨビ')),"
			  + "(UPPER('Jumat'),UPPER('Kinyobi'),UPPER('Kinyobi'),UPPER('きにょび'),UPPER('キニョビ')),"
			  + "(UPPER('Sabtu'),UPPER('Doyobi'),UPPER('Doyobi'),UPPER('どよび'),UPPER('ドヨビ')),"
			  + "(UPPER('Minggu'),UPPER('Nichiyobi'),UPPER('Nichiyobi'),UPPER('にちよび'),UPPER('ニチヨビ')),"
			  + "(UPPER('Januari'),UPPER('Ichigatsu'),UPPER('Ichigatsu'),UPPER('いちがつ'),UPPER('イチガツ')),"
			  + "(UPPER('Februari'),UPPER('Nigatsu'),UPPER('Nigatsu'),UPPER('にがつ'),UPPER('ニガツ')),"
			  + "(UPPER('Maret'),UPPER('Sangatsu'),UPPER('Sangatsu'),UPPER('さんがつ'),UPPER('サンガツ')),"
			  + "(UPPER('April'),UPPER('Shigatsu'),UPPER('Shigatsu'),UPPER('しがつ'),UPPER('シガツ')),"
			  + "(UPPER('Mei'),UPPER('Gogatsu'),UPPER('Gogatsu'),UPPER('ごがつ'),UPPER('ゴガツ')),"
			  + "(UPPER('Juni'),UPPER('Rokugatsu'),UPPER('Rokugatsu'),UPPER('ろくがつ'),UPPER('ロクガツ')),"
			  + "(UPPER('Juli'),UPPER('Shichigatsu'),UPPER('Shichigatsu'),UPPER('しちがつ'),UPPER('シチガツ')),"
			  + "(UPPER('Agustus'),UPPER('Hachigatsu'),UPPER('Hachigatsu'),UPPER('はちがつ'),UPPER('ハチガツ')),"
			  + "(UPPER('September'),UPPER('Kugatsu'),UPPER('Kugatsu'),UPPER('くがつ'),UPPER('クガツ')),"
			  + "(UPPER('Oktober'),UPPER('Juugatsu'),UPPER('Juugatsu'),UPPER('じゅうがつ'),UPPER('ジュウガツ')),"
			  + "(UPPER('November'),UPPER('Juuichigatsu'),UPPER('Juuichigatsu'),UPPER('じゅういちがつ'),UPPER('ジュウイチガツ')),"
			  + "(UPPER('Desember'),UPPER('Juunigatsu'),UPPER('Juunigatsu'),UPPER('じゅうにがつ'),UPPER('ジュウニガツ')),"
			  + "(UPPER('Ayah'),UPPER('Chichi'),UPPER('Chichi'),UPPER('ちち'),UPPER('チチ')),"
			  + "(UPPER('Ibu'),UPPER('Haha'),UPPER('Haha'),UPPER('はは'),UPPER('ハハ')),"
			  + "(UPPER('Adik Perempuan'),UPPER('Imouto'),UPPER('Imouto'),UPPER('いもうと'),UPPER('イモウト')),"
			  + "(UPPER('Adik Laki-laki'),UPPER('Otouto'),UPPER('Otouto'),UPPER('おとうと'),UPPER('オトウト')),"
			  + "(UPPER('Kakak Laki-laki'),UPPER('Ani'),UPPER('Ani'),UPPER('あに'),UPPER('アニ')),"
			  + "(UPPER('Kakak Perempuan'),UPPER('Ane'),UPPER('Ane'),UPPER('あね'),UPPER('アネ')),"
			  + "(UPPER('Ballpoin'),UPPER('Boorupen'),UPPER('Boorupen'),UPPER('ぼおるぺん'),UPPER('ボオルペン')),"
			  + "(UPPER('Buku'),UPPER('Hon'),UPPER('Hon'),UPPER('ほん'),UPPER('ホン')),"
			  + "(UPPER('Buku Catatan'),UPPER('Choumen No-to'),UPPER('Choumen No-to'),UPPER('ちょうめん の-と'),UPPER('チョウメン ノ-ト')),"
			  + "(UPPER('Buku pelajaran'),UPPER('Kyoukasho'),UPPER('Kyoukasho'),UPPER('きょうかしょ'),UPPER('キョウカショ')),"
			  + "(UPPER('Kalkulator'),UPPER('Dentaku'),UPPER('Dentaku'),UPPER('でんたく'),UPPER('デンタク')),"
			  + "(UPPER('Kapur'),UPPER('Chooku'),UPPER('Chooku'),UPPER('ちょおく'),UPPER('チョオク')),"
			  + "(UPPER('Kursi'),UPPER('Isu'),UPPER('Isu'),UPPER('いす'),UPPER('イス')),"
			  + "(UPPER('Komputer'),UPPER('Konpyu-ta–'),UPPER('Konpyu-ta–'),UPPER('こんぴゅ-た–'),UPPER('コンピュ-タ–')),"
			  + "(UPPER('Kamus'),UPPER('Jisho'),UPPER('Jisho'),UPPER('じしょ'),UPPER('ジショ')),"
			  + "(UPPER('Kertas'),UPPER('Kami'),UPPER('Kami'),UPPER('かみ'),UPPER('カミ')),"
			  + "(UPPER('Meja'),UPPER('Tsukue'),UPPER('Tsukue'),UPPER('つくえ'),UPPER('ツクエ')),"
			  + "(UPPER('Papan tulis'),UPPER('Kokuban'),UPPER('Kokuban'),UPPER('こくばん'),UPPER('コクバン')),"
			  + "(UPPER('Penggaris'),UPPER('Jougi'),UPPER('Jougi'),UPPER('じょうぎ'),UPPER('ジョウギ')),"
			  + "(UPPER('Penghapus pensil'),UPPER('Keshigomu'),UPPER('Keshigomu'),UPPER('けしごむ'),UPPER('ケシゴム')),"
			  + "(UPPER('Pensil'),UPPER('Enpitsu'),UPPER('Enpitsu'),UPPER('えんぴつ'),UPPER('エンピツ')),"
			  + "(UPPER('Pulpen'),UPPER('Pen'),UPPER('Pen'),UPPER('ぺん'),UPPER('ペン')),"
			  + "(UPPER('Seragam'),UPPER('Seifuku'),UPPER('Seifuku'),UPPER('せいふく'),UPPER('セイフク')),"
			  + "(UPPER('Spidol'),UPPER('Ma-ka-'),UPPER('Ma-ka-'),UPPER('ま-か-'),UPPER('マ-カ-')),"
			  + "(UPPER('Tas sekolah'),UPPER('Tsuugaku Kaban'),UPPER('Tsuugaku Kaban'),UPPER('つうがく かばん'),UPPER('ツウガク カバン')),"
			  + "(UPPER('Tempat pensil'),UPPER('Fudebako'),UPPER('Fudebako'),UPPER('ふでばこ'),UPPER('フデバコ')),"
			  + "(UPPER('Rautan pensil'),UPPER('Enpitsu Kezuri'),UPPER('Enpitsu Kezuri'),UPPER('えんぴつ けずり'),UPPER('エンピツ ケズリ')),"
			  + "(UPPER('Rak Buku'),UPPER('Hondana'),UPPER('Hondana'),UPPER('ほんだな'),UPPER('ホンダナ')),"
			  + "(UPPER('Staples'),UPPER('Hochikisu'),UPPER('Hochikisu'),UPPER('ほちきす'),UPPER('ホチキス')),"
			  + "(UPPER('Peta'),UPPER('Chizu'),UPPER('Chizu'),UPPER('ちず'),UPPER('チズ')),"
			  + "(UPPER('Pensil Mekanik'),UPPER('Sha-pen'),UPPER('Sha-pen'),UPPER('しゃ-ぺん'),UPPER('シャ-ペン')),"
			  + "(UPPER('Penghapus papan tulis'),UPPER('Kokuban keshi'),UPPER('Kokuban keshi'),UPPER('こくばん けし'),UPPER('コクバン ケシ')),"
			  + "(UPPER('Meja guru'),UPPER('Kyoutaku'),UPPER('Kyoutaku'),UPPER('きょうたく'),UPPER('キョウタク')),"
			  + "(UPPER('Gunting'),UPPER('Hasami'),UPPER('Hasami'),UPPER('はさみ'),UPPER('ハサミ')),"
			  + "(UPPER('Busur derajat'),UPPER('Bundoki'),UPPER('Bundoki'),UPPER('ぶんどき'),UPPER('ブンドキ'));";
			  
	  	String sql_ins3="insert into ms_kunci (tjml) values(0);";
	  
	  public DBadapter(Context ctx)
	  {
		  super(ctx, DATABASE_NAME, null, DATABASE_VERSION); 
	  }
	  
		  @Override
		  public void onCreate(SQLiteDatabase db) {
			  try {
				  
				  db.execSQL(DATABASE_CREATE);
				  db.execSQL(create_tbl_kunci);
				  
				  db.execSQL(sql);
				  db.execSQL(sql_ins3);
				  
			  } catch (SQLException e) {
				  e.printStackTrace();
			  }
		  }
		  
		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		  {
		  Log.w("", "Upgrade database dari versi " + oldVersion + " ke "
		  + newVersion + ", yang akan menghapus semua data lama");
		  db.execSQL("DROP TABLE IF EXISTS kontak");
		  onCreate(db);
		  }
		  
		  public Cursor SelectData(SQLiteDatabase db, String sql){  
		        Cursor cursor = db.rawQuery(sql,null);  
		        return cursor;  
		    }
		  
		  public ArrayList<String> getRecords(String sql) {
			    ArrayList<String> recordList = new ArrayList<String>();
			    String selectQuery = sql;

			    SQLiteDatabase db = this.getWritableDatabase();
			    Cursor cursor = db.rawQuery(selectQuery, null);

			    if (cursor != null) {
			        if (cursor.moveToFirst()) {
			            do {
			                recordList.add(cursor.getString(0));
			            } while (cursor.moveToNext());
			        }
			    }

			    return recordList;
			}
		  
		  public List<String> getAllLabels(String sql){
		        List<String> labels = new ArrayList<String>();
		 
		        // Select All Query
		        String selectQuery = sql;
		 
		        SQLiteDatabase db = this.getReadableDatabase();
		        Cursor cursor = db.rawQuery(selectQuery, null);
		 
		        // looping through all rows and adding to list
		        if (cursor.moveToFirst()) {
		            do {
		                labels.add(cursor.getString(1));
		            } while (cursor.moveToNext());
		        }
		 
		        // closing connection
		        cursor.close();
		        db.close();
		 
		        // returning lables
		        return labels;
		    }
		  
		  
		  
		  
	
}

