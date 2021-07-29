package com.example.videonotesph1;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ThumbnailDao_Impl implements ThumbnailDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Thumbnail> __insertionAdapterOfThumbnail;

  public ThumbnailDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfThumbnail = new EntityInsertionAdapter<Thumbnail>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Thumbnail` (`id`,`thumbnail`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Thumbnail value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getThumbnail() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getThumbnail());
        }
      }
    };
  }

  @Override
  public void insertRecord(final Thumbnail thumbnail) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfThumbnail.insert(thumbnail);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Thumbnail loadById(final String id) {
    final String _sql = "Select * from Thumbnail where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
      final Thumbnail _result;
      if(_cursor.moveToFirst()) {
        _result = new Thumbnail();
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpThumbnail;
        _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
        _result.setThumbnail(_tmpThumbnail);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Thumbnail> loadAll() {
    final String _sql = "Select * from Thumbnail";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
      final List<Thumbnail> _result = new ArrayList<Thumbnail>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Thumbnail _item;
        _item = new Thumbnail();
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpThumbnail;
        _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
        _item.setThumbnail(_tmpThumbnail);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
