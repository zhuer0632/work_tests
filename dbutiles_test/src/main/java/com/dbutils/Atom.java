package com.dbutils;


/**
 * Atom support transaction of database. It can be invoked in Db.tx(Atom atom)
 * method. <br>
 * Example:<br>
 * Db.tx(new Atom(){<br>
 * public boolean invoke() throws SQLException {<br>
 * int result1 = Db.update("update account set cash = cash - ? where id = ?",
 * 100, 123);<br>
 * int result2 = Db.update("update account set cash = cash + ? where id = ?",
 * 100, 456);<br>
 * return result1 == 1 && result2 == 1;<br>
 * }});
 */
public interface Atom extends Runnable
{
    
}