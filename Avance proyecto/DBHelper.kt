package com.example.tecnologias

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Tec(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String?,
    val imagenBase64: String?
)

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Tec.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query1 = """
            CREATE TABLE productos (
                id_producto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                nombre TEXT NOT NULL,
                precio DOUBLE NOT NULL,
                descripcion TEXT,
                imagenBase64 TEXT
            );
        """
        val smartphone ="/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAXQAAAAAAAAD/" +
                "2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQU" +
                "FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABdAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcI" +
                "CQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVW" +
                "V1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6" +
                "/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYk" +
                "NOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbH" +
                "yMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6KKKAGlgBRu/GvPvj3ez6b8I/E11azPb3EVsGjliYqynevII6V+Y3xF/bJvPh14" +
                "yi8O32oeI7yUJDJd3dvqJVbVZQGTEZOZPlZScFcZxkmrjDmV27Afr1u+lG6vye139onUtF8S6ZoNz4t1pNS1JHe2WO5mKELnlm3cZKsB9OccVp2fxT8VTajaI3ib" +
                "VmR5kUqb2QggsBj71bfV30Y7H6nZzyCKN2OtfhPY/Hv4mak0hHxF8Tk7mHy6tOAOf96p/D3xs+Jc/jPSrG7+Ifilrea4WN1/ti4HBOP79H1eTSdx8rtc/dEODQWr" +
                "8sPifpPxF+GcljdP8QPE2o6dezS2/mrqtyghkBZo1I8w/ejBGe5jPAziuLHxJ8bj/mdfEuP+wzc//F1X1SoupJ+watup1fJ//BP7xLrPiXwr4wfWtYv9Ymt9Sjjj" +
                "k1C5edkQwI20MxJxkk496+sK5pJxbiwCiiipAKKKKAPM/wBo+Ty/gl4vOP8Aly/9mFfkB8V/Fvw70z4haXb+JNMuL7WoI4mbUIbdWSzR/mi35YGTghuh2gjGT8o/" +
                "Xf8Aadk2fAnxk3cWJ/mK/Hn4lar8O4PG2lnxTHK2vrHD/pENsZIoEb5ofPIlXOB83EcmFK57KOiHwP1Gj0TUfEfhrT7y30vUFhOvXAzprsWLRIu7zMEKQN23Ayy5" +
                "KnrxU2lXp/trTgO9zFgf8DFYF9HbPdwyT28Utza7lildAzx54YK3UZ7460/Rrwt4h0tc9buEf+RFr0Umk2yj58tLPXfA3iHytYsLm0tLtjLFJNGQroxyrKehGCOa" +
                "9AttPEWvaXfHGEnibcO/zCvtf4U23hzx34A0vS9a0611azNpGjRXSBgCEAyD1U+4wa5X4g/sxQaDI+qeErsXNpFiQ6VeOFkULg4ilOAfo+Oh+Y1FJ8qsWmranNfD" +
                "b42W3xQ8Jax4Q1uYPeks9rM/JEqNujP5gVzA0qd4RNJbNBuJVlPRWHUe1fNWjau/gzV5tXN6wiMzNEtsnmOwLErnJAGR7/hX0t8N/ia3xC8Oai8lkLbG0qskgkZm" +
                "yPmyFXB68Y4z3xXS6sZLzMmfaP8AwTvQQ+GvHEfQ/wBqRHB64+zx819eV8j/APBPtdukeOgf+gjD/wCk6V9cV4lT42IKKKKzAKRjgUtNfpQB8yftY+O71tM8S+Fr" +
                "Mt9jj8O3F7eABTk74lTJKkjG49CM59q/MTxJ4S0XxNrkGq39kJr2FEiMgkZRMqnKiRQcPtwMZ9ADkACv0V+JKjxJ4S/aE8TN88UFiul2zk9Ng3yAfnFX51zXmP4q" +
                "7cNFSi7lF+e8Izlyx7k9TTvD15u8TaOM9b2D/wBGLWDPdkg81N4YuN3ivRR3+3Qcf9tFrveisM9U/Zn8byxq1hJKf3LfKPbNdf8AtgfGM+DPhJd2lpcGPVdaBsIN" +
                "p+ZUYfvW/BMjPqy14D8JtYHhrxVaXEjbbd22y/7pPWuQ/aY8TT/FH4lSCxLTaTpafZbcj7rNnMjj6nAz6ItcbdoqxJmeA/Cf/CWeH4YJlLQzxbd2eQRxkfQivR/g" +
                "FpN9oPiLXtFvEmV4bZWGR8nEij9d4I+hrf8A2YvCUeoeH1srkAXFtO231Kk5/qa+ifFnw/03w7a22qQRBL25KxSsB1VQT/h+VTC/MhS0TPe/2BgE0vx0mORqUB3f" +
                "9u0dfWNfKv7CMYXT/HZx8x1KAZ/7do6+qq5J/EwCiiioAKyvFGqLonh3Ur92CrbW7y5+ik1q15N+0zrsujfCnUoLfJu9QZLOFB1ZmPQUnsNHkusaY1j+w5441GUY" +
                "uNXs73UnJ6kO+EP4xqlfmHLdZr9ev2jtFj8L/sieMNKhx5dh4dNsuPREVf6V+N0k/UZ716GE+FjLUt1VzwrcbvF2hf8AYQt//Ri1hSTZ71o+EJM+MNB5z/xMLf8A" +
                "9GrXa9mM0bezknW3hg/1sqqB7ccn8BW5aeBVjH+p/wDr13nwb8BNrNodWmTMIVYYsjvj5j/IfnXrcHgCDYD5VcTV0PlPI/hjKfB2v2pb5IZpAhP1r6f+KuntDpPh" +
                "12GFukklH0AT/GvHfHngj7JoL3UEWJLeVJM+nOP617r8UGF54e8BY5zoscpPux/+xq4xskzKd9j0D9h9PLtPHYx/zEYP/SaOvqEHNfM37FqbE8eD/qIW/wD6TJX0" +
                "1Xny+JjCiiipAQnFeJ/GT/ipvip8OPDA+eP7d/aU69tsIMgz7Epj8a9rfpXi3hYP4n/aT8Sai3zW+haZHZIewklbJ/SNvzqWNFn9rsbf2ZfiR/2Bp/5V+JhcjvX7" +
                "ZftgnH7MfxJ/7As/8q/EUvgZr0sL8LGh7zHPHIrU8GSs3jHQPlwP7Rt+/wD01WsTzM9q2PBrE+MdAI6/2jb/APo1a7WtGB9qaTo3iLwz4B0EaRY2ZtzYwyDzVdiS" +
                "yBiTgjkkk1kw/EXxZaXXkz6dppGcEmGUf+1K940rxVpun/DLwypt0Z10q1BMzcZ8le3/ANevmT4veKLnUrx/s14Yo8keXaqEUfiOT+dcad4o0ujc1r4kX+rXlror" +
                "W2ns00irOULKEXIycljzivaPE8Ua2+h2MN7HqMWn6dFapcxA7XALMDz/AL2K+bvg54LufEmoG8uoXj0m3bMhbrcP2X6ev5d6+hDCQMdP5VpG9jGTuz2T9jtNk/j4" +
                "f9RC2/8ASaOvpOvnD9kJcXnj8Yx/p9t/6TJX0fXmS+JgFFFFSAjDIxWF4e8Haf4ZvNXu7OMi41W6N3cyMclnwAB9ABwPr61vUUAct8SvAVn8T/AuteFdRlli0/Vr" +
                "drWd4ThwhxnB7Gvlk/8ABLH4Ysc/2zr4/wC26f8AxNfZ2MUtUpSjswPi4/8ABK74Yn/mOeIR9J4//iKsad/wS9+HGlX9reQa5r/nW8qTJvmjI3KwYZ+TpkCvsmin" +
                "7Sfcdz5if9hfSZoEt5PHHiL7JEixwwqLfEaAYC8xnOBgc1Qn/wCCe3ha5kV5fF3iGTb0DC2wPw8rFfVlFTzS7hc+cbL9jCw062jt7Xxz4gggjGFRIbQAf+Qasj9j" +
                "+2ByfHniI/WO0/8AjNfQtFPml3EeefCP4OWnwkj1ZbbVbzVpNSmSaaa9CBsqgQAbFUYwB2r0OiipAKKKKAP/2Q=="
        val smartwatch ="/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAZAAAAAAAAAD/" +
                "2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQU" +
                "FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABkAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcI" +
                "CQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVW" +
                "V1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6" +
                "/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYk" +
                "NOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbH" +
                "yMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9PaKKKACmyypBE8kjrHGgLM7HAUDqSaravq1loOl3epaldRWNhaRNNPczuEjiRRlm" +
                "YnoABX5U/tdftrav8bNTuvDfhW4n0rwJC5QhSUl1PB+/L3Ef92P8W5wFaVwPsP4s/wDBQX4a/Dq6n0/SJJvGWpxEqy6awW2Vh2M5yD9UDD3r548Q/wDBTbxpqMjL" +
                "o+gaPpUJ+75qyXEg/wCBFgv/AI7XxLDETWpZWvmOqjvWyiibn03L+338Xr5i0Wt21qvXEen25A/76Q1ST/go98StJuMT+JLS/IPMb6fBj80Rf516J8H/APgn7F8U" +
                "/gjda14g1W+0PVtWgE+ix2oUiKPGUkmU/fEnBCgrhSDnJ4/Oe/0T7JeTQzLOkkTlGRyVZSDggjsaNOiCx+lXw2/4KnRz3UVv4v8AD8E0DEBrvSXMbqPXy3JDfgy1" +
                "9r/DP4t+E/i/oY1XwprEGpwLgSxKds0DH+GRDyp69Rg44Jr+f63tbaNhlp4/dXz/ADzXpvwn+KXif4ReJLTX/C+sSQ3FueSv8Sd0kTo6HHI/wzUuKYz95KK8c/Zm" +
                "/aS0T9orwYt9beXZa/aKq6jpgbJjY9JEzyY25wexyD0yfY6y2GFFFFABXF/FX4v+Gvg34fXVfEV20YlYx21nAoe4unAyVjXIzgcliQqjkkV2lfkJ+294puPir+0l" +
                "4jmvpWn0vw1IdF0yzY5jiKAefJt6FmkLc9cKo7Ct6NJ1pqCMatRUoczO5/al/aV8Q/tC2MOgWd3pfgvwpG/mTWcuqwzz3rg5UzFGAVRwRGM88knC4+bE+HVqo+bx" +
                "joSn/rqp/wDZ656XT5Hby4I2Y9lQVTvdCv4Iy8ltKFHJbbnH1r2o5a7aS/D/AIJ5TzBXtb8f+Ab+veG7Xw/p73f/AAl2iThf4ElQsfovmbj+ANc94P8AGkE/iNLD" +
                "UIw0O4ZmtydsiAgsBnvtzjmsWe3SZTHMgkQ9VYZBr06L4eabdfDldaijMV1HEh81Tg7hkf0H4k1y18M6Fne6Z1UcR7V2tZn7jaRNZXGlWUunNE+nvCjWzQY8sxFQ" +
                "UK44xjGPavy4/wCClH7O9j8O/Hdp480YRQab4omkF3ZrgGK9A3O6j+7IDuPowb+8BX1F/wAE1/ibdePv2fF02+na5u/Dt9Jp6uxyTCQHjH4bmUDsFA7V87/8FVPF" +
                "Fzq3xP8ADHhlXP2TStIN+FzgGaeV1b6/LAn5mvMSalY773Vz4QaEZpbeZ7OYOjEc84q3JAwyGUo44ZWGCCOoNVpYsVoB6h8Gvi7rXwX8faV4m0G48qRXw8RJ8uZT" +
                "9+Jx3Vh+WMjBANftV8LviLpnxX8B6R4p0gn7HqEO/wApjloXBKvG3urAj3xkcGvwY0Wa2aMQ3m8xhvlMfVXwdje4BIyO4zX6tf8ABNO5u5vgdrCTFmtotbkWHd2/" +
                "cQlgPbkH6k1ElpcEfW1FFFZFBX4z/Fi3a/8Aip41kPLza9fOSfUzuSa/Zivx9+IkKr8RvFzdzrN7/wCjmr08v1rfI87Hu1L5nG21gtsBHCmSeuByTUzxPF99dtep" +
                "aN8MJL3wNDq9qS+oS75GhPeMHA2+/Gfx/LgEtDqM7ghokRiHLjbjHX6dRn6jgkgH7CjiItuK2R8jWw84pN7s4fXPAMmtSC506NI23ATbmCrgkDcPfkZA9a2fia48" +
                "DfCjRrOFvPikumilaTOH4jY5A7HBGPQ1oa3dvbXy20Z2W8e1kAGN3ufxz/8AXJJN34m6Tb+I/hekM2RhpJY2HVWAiwf1rxMxmpq6Vlf9Ge3l0XB2bvp+qN39kP4k" +
                "Xngr9oHwRbaNcSCPVr6PTb6CNiEuIpDtO9RwdpIcHHBXPrXu3/BUn4VXP2vwz8RbRC9v5X9i3+B9whnlhb8d0oJ9lHeuH/4JQfCXQvFfibxJ431eOS81rwy0Nvpo" +
                "L/uYzMsyvLtxkuAm0EnA3McZwR96/tM+CIPiH8BfG+izRiR20yW4gyOk0Q82PHp8yKPoTXzjdpH0S1R+HMkElxJJI87lnYuxJySSck0w6Vu6yvV8x7XI9DTlWrEQ" +
                "6bpUcFwrlmcg5Ga/a79k3wNa+AP2fPBllbMsj3ljHqc8q/xyXCiU/XAZVHsor8Y7SP77f3VJ/Sv2x/Zsujd/s/8Aw8cnJXQrSP8A75iVf6VE9ho9IooorIoK/H74" +
                "iMf+E98WN663fD/yO1fsDX5B+PbczeLfGBUZdNe1Dp7XD16WA/iv0POx38Jep6f4T8TeX4C05rZwJorfYvOPmXj+Yrwzxd431TXfFk4v4YrJIf8AljBEIldyMmVg" +
                "PvSNnl+pwKt6H4pm0aJ7c5e2c7to6qfUVw3izVftfii9njJKNsxn/cUV79KmuZs8arVbiol7V78XN+pB+6gU/qf611fiAtJ8N7dVBYkTDA9cRV5vDNvkBY5JPPvX" +
                "pV03m/D+yP8AtzfzirjxytBep1YLWb9P8j6I/wCCQq7NB+J69T9rsckdOlx0r718XMi+FNaMmPLFlMWz6eW2a+DP+CQ4/wCJD8Tz2+12P8rivtD476udB+CnjzUF" +
                "OHg0O9ZD/teQ4X9SK+en8TPejsfh1LHmVvrSKnpXTeGdAttYgu5J7iJJB8kUTtgsSOSP0/Op7XwbDpXgeW4kiZrosJllUfKoLYxnvkGqcrOwWOegXbbTH/Zx+ZxX" +
                "7SfsvxtF+z18P1br/ZEB/Arkfzr8YIlzBt/vyIv65/pX7f8Awe0c+H/hL4K0xl2vaaLZwuD/AHlgQH9c0TBHX0UUViUFfk58RrNtK+LXxF0uQbJbXxHfHaeuySQy" +
                "xn8UkWv1jr4k/bb/AGVfEuv+Lv8Ahafw7R7vVHtkttd0OOHzWvUjGI540yC0ir8pCnJAXaGOQerDVVSqcz2OXE0nWp8q3Pj/AFHSQzl4sKTyVPSvKvFOqx2PiG7t" +
                "3DGVNmQOnKKev412tz8RDZ3ktjfx2NpfQnZNb3ctxbSxt3DI8GQfauD8R6MniLxBdamms6Pbibb+6Ny5xhQvXyx6ele8sZTjrF/gzxVhKr0kvxQWOomaQE8e1ena" +
                "3rVvpvwwjEkwjk2OyYPPzbRn80NeOavp95o9g8llqWk3VwB8iRzSOx+gEePzIFZXka9411Wx066mItQqRC3gbcxA6D6sxP4sa4cRXjVSUdTvw9GVNts/Sr/gkVpM" +
                "sfwx8cawUYQ3mqQ26uRgFo4yzY/7/Cvr746eH5PFXwY8caTDjz7rRruOLJwN/lMVyfTIFZv7OXwXsfgH8IdC8H2QVpbeMzXky/8ALW5fmRvoDhR/sqtcX+3D8Vo/" +
                "hf8AALW4ophHq2vqdIs1B+b94CJX9Rti389iV9a8hvmldHppWR+SEGuXOk6bf21si+dOu0SdGXsQD7/0qS21+7HhyHStzJFhRKueGx0H8qxLuWeFjtj85R0IOD+N" +
                "Vxd3c64VBAvdmOT+ArSyvcLnffDPw2/jr4h+HfDsILG9v4LZivYyOEH5Bia/c5EWJFRFCoowFHQCvy1/4JwfC1vEvxgXXZYS9j4dt2u5JGGQ1xIDHEp9+Xcf9c6/" +
                "Uuom9RoKKKKzGFFFFAHz5+2D+yZo37S/gtnhit7Lxrp0ZOl6m6439/s8p6mNj0P8JOR/EG/G3xR4K1HwR4hv9C17S5NM1axlMNxaXEeHRh/MHqCOCCCMg1/QrXgn" +
                "7UX7IPhb9pXSVuJ2Gi+LbWPZZ61DHuJXqIpl43pnpzlc8Hkg3GVhNH4sQ2dtkZhjP/ARXTeGrsaVfQzQbYXjYOjKB8rA5B/MV6B8XP2R/ib8Frqf+2/Ds9zpkZO3" +
                "VtNU3Fo6+u8DKfRwp9q8rjSeBhlGGPUVrck/c34Y/G3w78RvhRa+OVv7ey09LcyaiZZAq2UqKDKjk9Np6Z6gqRwRX5c/te/tDyfH/wCJb3lm0kfhrS1a10qB+CUJ" +
                "+eZh2ZyAfYBR2ryHT/FuqReH7zRE1K8tdMvHSS4tY5mWGZkzsLoDhsZOM9Kx54ZwpC7XJ6MDx+VJRtqO5D9o8m5QkLIQQ2xhuU4OcH2rTsre98V64CsRubu7nAWK" +
                "3hC75GOAiRoAByQAqjHQAVY8HeANf8e63DpWg6Td6xqEx+W2sojI31OOg9SeB61+mP7IX7EkPwhlt/FnjJYLzxYq5tLGMh4dOyOW3dHlxxkcLzjJ5obsB6p+yj8D" +
                "l+BXwnstLukT+378/bdUdSDiVgAIge4RQF9CdxHWvZaKKxbuUFFFFIAooooAKKKKADrXE+Jfgj8PvGEjyaz4L0PUJn+9PLYR+af+Bgbv1oooA4m7/Yt+C165aTwJ" +
                "aKT/AM8rm4jH5LIKdp37GPwX0uUSQeBLNmHOJ7ieYfk8hFFFO7Eep+GfB+heDLH7HoGjWGi2veGwtkhUn1IUDJ9zWvRRSGFFFFABRRRQB//Z"
        val laptop ="/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgC" +
                "QABAAAATwAAAAAAAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEB" +
                "QkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABPAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAA" +
                "AAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3O" +
                "Dk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5" +
                "ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRo" +
                "bHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqs" +
                "rO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9UTIoGSRj1rjfF3xq8AeAVc+I/GugaGVGSl/qUMT/AIKzZ" +
                "P5V+Vvx70bxXeftFeO/DXjTx14pvtOtNUMttpx1WVLb7HN+8h2oDgDaccYxjFe5wfst/CPSvhnpWuaL4M09rwsDLcXhe7ZyRg581mwc4PGK7I4acoqV9GcU8VGMn" +
                "Hqj3fxD/wAFGfgZpFy1rp/iW78U338Nr4f0u4umb6NsCH/vquOuv2+vF/iS5+z+BfgT4gv0kYJFdeJb+HSV54DFGDkjpxkVwuh6XaaTCIbK1gs4R0jgjCKPwAxXW" +
                "aaTG6FeCCCCK6Vgl1ZyvHPoin/wvn9pLxYrKI/APgyNz8vlQ3N/dRj33OIyaran4e+Imq2E9/44+Onic2ca75I9Dit9GiC9xmJC/wD49mty4vZrC3eW0sW1KdScC" +
                "KVEx7Hca8h8faP8QPiTcLFqFxZ6BpCHK2ySeYfqQOGPuSPYU6eGTlqtBzxLUdGd1+x34+0vRPj9eeH9PvtVubHXrKRBJq2pz3ks9xCPMR2MrttPlifhQO2c4r73B" +
                "yBX40aFeX/wG+M+gate3G6LSr6G9+09BNb7sSgD1Kb1x71+ysMiyxI6MHRgCGU5BHrXPjKcYVPd2Zvg6jqQ956ofRRRXCd4UUUUAFFFFAH56/8ABSTwUPC/xC8Gf" +
                "EGCMC21O3k0S/foFlT97AzHuSDIvPoKyP2fPjFp3ivwlqXg6/uVhuXUyWUkh2qzjnZz69Pxr6t/bW+GDfFT9nHxZp9vGJNS0+AatY5GSJoD5nHuVDr/AMCr8+vhV" +
                "4Bg1m003UoQVhniSdWH91gDz717GFqp03CXQ8fF0nzqa6nvdoj+eY9pDgnIPGMdc/SuC8d+OZbhJre1nMVjH8pdDjziOp/3fb/9Vdf4q1f+yvD8emWpETXKbWcfe" +
                "MY6kn3/AMa+fvGOsebL9ni5UfKo9TXqUI+01Z5VV8jshvgn4pT+DfiBBKJXNhcsIbqIfdZSfvY9R1H/ANevqHUrpZYQ6sGVhkEHgivjaXRGcb0JBTlnHUt6Cvozw" +
                "V4hOreDbB2fdLGnkvznleP5YrSpFbkQOW+L3hS18SWdtcyIrTwSFV4GSD25r7//AGYfFj+Lvgd4WnmcyXdlbf2bcFjljJATFub3YIr/AEcV+ffxO1aa00NpIcl45" +
                "MkDuD/+qvoz/gnd4/bUrDxZ4cuHO4SQ6pbqepDL5UoHsvlwn/gdeTjYXgpdj1sHK03HufZtFFFeKe0FFFFABRRRQAyaNZonjdQ6sMFT0Ir85PCHhVfh34s8a/D9g" +
                "I5NB1mRLYN1NjP+/gf6bXZf+AV+j1fD37bunv8ADn4o6N41tYiLfxPpU3h+6ZeAlzGfMgkb3KGZB+FbUX76XcwrL3Wzw/x14wW6uL66Rx5Tt5MHtGvH6/1NeQxXD" +
                "ahqDy9dpwo/2jU/jLWth8ndxEu3r371R8NMFKSOeEUzMT+n9K+tp2hGyPl5pzdzqXgSOERDHyD5sevetPwb4lHhm4ntrptljMc7yOI39foe9eV+Jfjl4e8MkxRyn" +
                "WNQyf3FmQVB9GfoPoMn2rgtS1zxl8SCqXci+HtJc8W8IIkce/8AEfxwPauariKcfdWr8jenhpvV6I95+KHxl8JeH45rS5u01W6YY+xWREje24g4X8TmrX7Inxr8U" +
                "eB/i/F4kn0JLLwvHZT20ljLLtmlWTaVIYrnh0Q9AMCvIvCXgLSPDXlypCrXXG2WfDysc9uy/hz9a9D0292LgLhccr1I7Akg9eTwfyryqlR1FaT+R6dOkoNOO/c/S" +
                "jwt+2f8OdekSK9vLrQ527XkBaPPs6bhj3OK9f8AD3jXQPFtv52i6zY6rH62lwsmPqAePxr8kLO4LMVfcjO3pjI9P/rY9K6DS7z7BKs8UkkNyOUlhO0gY6g5BH1HH" +
                "pXG6Sb907faNLU/WkMG6UtfnP4U/aM8feFtqW/iO5u4FPywagRcjHpl8t+RFe4eGf227X7LCmu6HL9oCgSTWbAIx7kKTn8M0nQmugRrwe+h9UUVx/gD4mWHxH8Ox" +
                "61pdjfxWbu0ai7h2MSMZIwSCOcZB7H0orCzRspJnYV4X+2p8PZPiJ+zz4kitIhLqukKutWI/wCm1ufMx+Kh1/GvdKjuII7qCSGVFkjdSrKwyCCMEUJ2dxtXVmfz4" +
                "eMfjBp7MxtS93M/zEcqqk84J7n6Vhed4l8cxbr++/svSpMAW8PG5R0Gzqf+BGvsr4rf8EyPEXgPUtU1rQLNfE1lLdz3EX2EfvraFnLIphY/MQpAyrZ68Gvny88PX" +
                "WgTzWtzaSJdwsyyxSoVMeMZ3RsAy49SK9J1pVV7z+R5ypRpu0V8zA8NeELHSIhLbW4QjrdTfNJ16g9vw/WuqgZLVcRlfMPysZMFsn0APTpzmqMdy4QyqQxjYYkO0" +
                "kqeowR/j36ipIZi/mABmK5TJzhh6Mc9uTwP06Zcz2Rsorc2LZjIzEjBY7T7HgYdjj1HT0rdtbhSMDcx65JCgdc8Dp9etcnJfw2YRjKkrMowiIQVBzwc45HHTI56n" +
                "GKntNfwoITEp7tgqv0Hc+59eAMZpLUG7He20zQhX3GPOCu1iCR6+uOnWti1n4Gwhv8Ad/wrg7LUmlbc7lmJyWJ5rptBhvNc1C3sNPtpby8uHEcUEK7mdj0AFdMfd" +
                "MJXkdLBcsWAAJPoOtfUvwB/ZYufEYtvEHjGF7bTOJLfTG+WSf0Mn91fbqfYdev/AGc/2SoPCC23iHxikd9rmA8Ng3zRWp6jd2Zx+Q/Wvp9VCgAcVzVa9/dib06Nt" +
                "ZENnY2+n2sVtawx29vEoSOKJQqoo6AAcAUVPRXEdgUUUUAIRkYrgfiX8CPAvxctlj8T+HbPUZo/9Vd7fLuIj6pKuGH5139FAHwD8Vv+Cac6GW98Da4Lsj5lstVPl" +
                "zfRZ0HPb76n618d/Ev4OeOvhhO9t4g0S508gkCe6iwh/wB2QExt7DdnnoK/cKqeqaPY61aPa39pBeW0gw8U8YdWHoQa1VRrczcFbQ/AItNbysk6uso+8JAQwPvmr" +
                "ttcYI54r9W/it+wB8OfHiSzaRC/he+IJH2QBrfP/XI8KPXZtJ9a+W7v/gmt48g8a2enW+o6ZJoksn73UhMQ0SZ5IiIznGcDLdOTW0aie5g6ckeEfC7wB4g+Kfia2" +
                "0Tw5YyXt5KfmKj5Il7u7dABX6lfs5/svaH8ENKjupgmqeJ5kHn6g68R5HKRA9B79T+ldZ8FPgV4X+BnhePSfD9oBIwBub2UAzXD46s38h0FejVlOo5aI2hDl1YgG" +
                "KWiisTUKKKKAP/Z"
        val audifonos = "/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAaAAAAAAAAAD/" +
                "2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQU" +
                "FBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABoAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcI" +
                "CQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVW" +
                "V1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6" +
                "/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYk" +
                "NOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbH" +
                "yMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6KKKACisrxR4o0nwVoF9reuX8GmaVZRmW4urhtqIo/mewA5JIA5r81f2i/+CjHi" +
                "PxtPe6R8OpH8L+HIyUk1iU7LuceoP/LIdwF+bpkjOKaVyW0j9DfHfxk8EfDKMt4n8T6do7AbvJnmBmI9RGMsR9BXhniD/gpH8GNCnaKPUdS1Mjo9paAKf++2U/pX" +
                "5Q6Aniz40eJZ7HwZ4c134j62X3TzQRu8MZJ+9I/3VBPd2x71794W/wCCbX7SXiS2SW5Pg/wRGwyba7vTLKv/AH4jkXP/AAKqsibyex9mWv8AwU9+Ds8oSQa3AD/E" +
                "1rGQP/Ilem+Cv2zPhB46kjhsvGNpZ3MnSHUQbf8A8eb5f/Hq+Bbv/glr+0DZxGS18c+CdQcDPkzSXUefYHyDXjfxL/Zb+OPwctpb3xV8MJdT0qLLSav4VlF0qqOr" +
                "Msfzqo9XUCjQLyR+4trdwX1vHcW00dxBINySxMGVh6gjgipa/DL4C/tbeMfhbepceDPE01zZK26fRNQy8b+uYycE+6bWA6V+qX7MP7Xnhf8AaP0s20ONG8V28e+6" +
                "0eV8lgODJC38aZ6jqvQjoTLQ1K+h73RRRSLCiiigApGYIpZiABySe1LXzD/wUG+N0nwi+Bd1Y6fOYdb8RlrCBkOHSHGZnX32kJ/20z2o3E3ZXPi39ur9q1vjP4yu" +
                "vD2kX5h8AaBIw3xthb6dcgzH1HUIPTLfxYrI/Y3/AGINS/askh8Y+NftWhfCm3lK2WnwMYrjWWU4bDdUhyCC45Y5C45YeP8A7OfwRuv2m/jvoHgBmkj0GHOp69cQ" +
                "nBW1QjeoPYuSkYPbeD2r92tE0Ww8N6PY6TpdpDYabYwpbW1rAgWOGNFCqigdAAAKtu2hEVfVmb4G8AeHPhn4btPD/hXRLLQNGtV2xWdhCI0HqTjqx7sck9STW/RR" +
                "UGgUUUUAfH/7X/8AwTt8IfH+0u/EvhGK28GfEqMGWLUrVPLt79+uy6RRyT080DcO+4DFfl54d1zxl8JfiLPaX0d14W+Ifhi7xLGPlkEi/wAQ7MCMcjKupHUEGv6A" +
                "6+Av+Cqv7NaeJ/A1v8YfDtt5XibwuFTUzCvzXenlsbmx1MTHOf7jPnoKpMiSufTX7LH7Qun/ALRnwwtddiEdvrVqRbarZRniKbGdy99jj5lP1GSVNex1+Mn7Afx3" +
                "k+Fnxw0lbify9B8SBbG9Qn5FLH5JPbY5Bz/dZhX7N0mrDi7oKKKKRQV+Sv8AwU5+JB8UfHuDw/HLusvD9okbKDx5hHms31yyqf8Adr9aTX4B/tMeOR4m+Lnj7X5Z" +
                "QwutRuDFludrSMQB9ABVIzn2Pvj/AIJB/DZLH4d+NPiFcwj7brupjT7eRhyLeBcnafRnkYH/AK5iv0FrwP8AYP8ACS+DP2SPhtZCPy3uNN/tB8jBJuHafJ/CQD8B" +
                "XvlJ6stKyCiiikMKKKKACs3xJ4fsfFnh7VNE1OEXOnalay2dzC3R4pFKOv4gmtKigD+d7UdBu/hn4017wzcuTfeGdYmsWPQt5crR5+hxmv3i+Afjv/hZfwa8IeJG" +
                "k82e90+MzvnrMo2SH/vtWr8gf2/vDcXg/wDbN8d2iqIY9Zht9ShLcAmSFd5/7+K9foH/AMEw/Fp8Rfs4Gyd9z6Vqc0CgnOI2VHH/AI8z1b1RlFWZ9dUUUVBqIRkY" +
                "r8L/ANpH4Q+B/hF+2cuhRTTaj4Ttta0yS5h1acSoFkaJ7hZDhQUGXB3dB1NfuPqupW+jaZeahdyCG0tIXnmkPRUVSzH8ADX8/Pxh1Of4veKvF3ji+LONR1aadYie" +
                "NpYkfgMgD6UEs/Vfxb/wUq+C/g2SPS/DtzP4mFuRABpMIjtolXgKrNjIA6bQRxwa+g/hB8XvD3xs8GW3iTw5cNJayEpJDLgSwOOquATz79DX87+lWws7jEYwh9K+" +
                "s/2Kf2nLz4A/Eq1i1Odz4S1JhDqURyRGhP8ArgPWM/N/u+YOpqkronmaep+0dFRwTx3UEc0MiywyKHSRDlWUjIIPcGpKk0CiiigBOlfKXxJ/4KO/DT4b+PLjw5Pb" +
                "6jqkVrJ5VzqNiEaNX7hQWG4D149s0n/BQD9pkfBT4dr4d0a4CeKtfjZVKN81raD5Xk9mYny1+rEfcr8cdeuLm8eWe4ZpJ5mLMx6knqapLS7M5S1sj7H/AOCmXxm+" +
                "FP7QmkfDfWvBWpWWq6zFPe213cxRmK8t4wiNHHJkBthYuV6rkNjvX2p/wTm+F2gfD/8AZq0DU9Eury7m8RRC+vnupxIonUsjBAFG0DaRjk8da/FTwv4Wh1XWIIwC" +
                "kmdxkXqtfrV/wSk+Ibax8IvEPgq7lze+G9SYohPIhlyQB7B0f/voVJS1Z9x0UUUFHM/EzwZH8Rfh/wCIPDE1/daZDq1lJaPd2YBljV1wSoIIPGRjuCa/DzxX4fs/" +
                "Cmm3GiW8kk1vbtJGHnUK7fMeWHY1+9Nfk1/wU3+DU3wx8fxeLdMgK+HvEzs7lB8sF4OZFPpv++Pff6UyWj4y8NWUVzKwODsbaRXR3+jPEo8riRP3kTEfp/SvPtC8" +
                "QLpevo0h/cTEK+ex7GvZolW+scD/AFqDcmep9vxoiQ9j9G/+CbH7RC/ET4eP4B1a5La54ciBszI2Xmsc7VXnqYmxGf8AZMZ719n1+Cvww+KepfAr4saF4x0ZiXtr" +
                "gO8G7CzIflkiPtIhK+x2n+Gv3O8D+M9K+IfhDSPEuiXAutK1S2S6t5R1KsM4I7EcgjsQRTkiou+huVkeLvFemeBvC+q+IdZuVs9K0y2e6uZ36KiDJ+p44Hc4Fa9f" +
                "nN/wVE/aP8qSx+E2h3QMmY73WTG3VuGggPsOJWH/AFy9TSSuU3ZHx38bPinqnx8+K+ueLNU3RpNN+6tmbIt4lyIoR/uL19XZz3ridW0VYrXMgxIRk+3tW34d0+KG" +
                "FAcMkQ3MT/E3b/GuZ+JPiFdPtPKQ/v5/lT6dzVSZkt7jPAHlRalNIMddoNfpT/wTO+FdlY6r4y8eQalfC5uQmnyaeFUWpUhH35xkuCvrwG96/MD4b2t/r3iLS9G0" +
                "m3e81G/uEtre3jGWkkdgFH5mv31+Avwntfgt8LdE8LwFZLmCLzL24Uf665bmR/pngf7KqO1T0LS1PQqKKKRYV5n+0d8E9O/aC+EGv+DNQ2xyXcJeyumGTbXK8xSD" +
                "6Hg+oJHevTKKAP5jfHehar4G8Wav4c1u1ey1fS7qS0uoH6pIjEH8OOD3GK6nwv8AEib+zEhmfM8KhCT1YDof8+lfff8AwV7/AGUy0lp8avDlnkHy7HxFHCvTosFy" +
                "cfhGx/65+9fl/aRNDMCCR2NFieh6BqHiQaqZY3P3skD+dfpV/wAEkv2hn1fT9b+F2r3e6e1Y6hpqyNzg/wCtVfY/fx2PmH+IV+VxtnTbKp+ZeRX31/wTB/Zu8T+J" +
                "vippPxVMMuk+F9KEqi5kypvJCpXykH8SgnJPTjHWqTurEWs9D9TPi58R7D4SfDfxB4t1J0W20u0ecLI20O+PkXPuxA+mTX8+Hi74kah498a634s1a4e4vNQupLl5" +
                "ZOrFmJJx2zngdhgdAK/cT9tj4I6z+0D8AdZ8LaDcLFqoljvIIXfYtyY85iLdBuBOM8ZAzgc1+D3jHwzqXhfxDeaBqllNpt/p0rQ3NtcIUdJAcEEH0oTshy1Z0Nj4" +
                "/a3tSrNheprz3xF4pm1/VnupGOxQEjU9lH+c0mrWz2wMJPJ6gHNdV8BPgdrfx/8Aix4f8EaGjC41KcLPc7crawLzLM3sqgn3OAOSKjfUpdj78/4JF/s3Prup3nxg" +
                "161P2GxZ7LQklXiSbGJZx7KDsB9S3pX6r1zfw3+H+jfCvwJofhLw/bC00fSLVLW3jHXao5Zj3Zjlie5JNdJTGtAooooGFFFFAFTVLCHVLCe0uIIrmCZSkkU6B0cH" +
                "qCp4I+tfMfxN/wCCenwr+JVxJcTeGNK0u4ck+ZpVt9k59cRFQT9RRRQB594V/wCCT3w10DXY7+6vNS1OCMhlsbqZWhBBzyAoJ+hJFfY3hHwTZeDdLttPsB5VpboI" +
                "4oUAVEUdAqjgD2FFFAG+8YdSCT+FfPP7QX7EfgP9oef7ZrkEttqwXYup2ZCXAHYFsEMB2DA47UUUAeU+Fv8AglB8KNAuEmvPt2uspz/xMZyQfwTaP0r6i+GHwU8I" +
                "/COz+z+GfD+laNldjyWFlHC7j/aZQC3QdSaKKAO9ooooAKKKKAP/2Q=="
        val tablet = "/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAZAAAAAAAAAD/2wB" +
                "DAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQ" +
                "UFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABkAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQo" +
                "L/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1h" +
                "ZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8Q" +
                "AHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOE" +
                "l8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMn" +
                "K0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6hnuEto2eVgiDkknAqU9K4r4taRqHiHwdqGmaVqb6PqV1bTRW1+i5NvKUKrJj2Jz+F" +
                "S3ZXLhHmkkdB/wk1gf+WpxnGcGuS8f/H/AOHnwrtornxf4u0zw7BK2yN7+fy/MPcKDyfwrzL9mz4c+LPhX8M49E8ZeMpvHGqi4lmXUZ5HkKRsQVjDuWZgOeWJ64H" +
                "AFfml/wAFJrh/En7W2l6XfO9xYQ6LGY7dmIVSXmJxj1KjP0FTBuc1FHdDCe1lGEd5NLXzP1BP7en7PYH/ACVjw5/4EH/CmH9vn9ntevxX8PfhM3/xNfhl4p+Hlom" +
                "gTTaZZBLqIhsIWYle/BJ/yK6Cw+Dun2ejWq3tmJr0oGlcsw+Y8kcHHHTp2rveGmpcrPoqPCmPrV5UI291J31tr023P2sH7fP7PR/5qv4f/wC/zf8AxNOP7e/7Pgx" +
                "n4reHxnnmZv8A4mvwc8caJovhWNUWzVrqQfJGXbgep5rm/CmjnWb9zLF5ltEuWz0z2GahUJOoqaerPFxWV1cLilhJSTn5X09dD+gYft8/s9k/8lX8PD6zN/8AE0p" +
                "/b3/Z8AyPiv4eP0nP/wATX4PSeFNOjBY2q4AJPzH/ABrnDZ2cZZnhXYOuSeK6qmBqUrczQquWVaFudrU/oO0b9uL4D+INTttPsfil4de7uHEcSSXYjDMeg3NgDPu" +
                "a9xjkEgyMEdiK/l/1KwtF055oYwDgMrAk96/ok/ZA1G51f9lv4UXt5M9xdTeGNPMkshLM5FugySep461xVaTpNJs4K9B0Jcsmev0UUVkcwVg+Jh8kLehNb1c94uf" +
                "bbQe7GonsbUfjRgb0RCFwMDgCvxz/AOCg2pW9h+2JZXN0+yAaTCjOei5knGfpzX7ANPwea/GL/gpNZz6p+0+ILaJ5530iEKiDJP72eooNqomj1OedKcKkFdppoXw" +
                "7pa3eoWyDDByCMcgjGa7e68OOynKH8q8P+APiTWfC/jDRbDxFbNHoZl2Nd3JA+zKQQCT/AHc469K+4NJ0vQfEtsZdM1Cy1JB1a0nSXH12k19JWr89mk1of0FkGYU" +
                "sVhnJpxlfVPR7fij4O8V/CLUfEni2+v7zUIILMybY44lZ3VBwBggAH+tdHpPgKLT7ZbDTbV5D1IC7nc+prvfESW+m+Nrjw+93brqT3v2WK2aVRI7M+EAXOecrj61" +
                "7/a/DKHwxoi29vGHm2/vZ8cyNjn8PQdqvFYjD5ZT9pSjzTfmeRl+R4Ovi6k0/ebfM73er28j488U/DfxCmluLPSpZ3fhvLZSQvfjOT6cD1rw7xVBcabP9iuIngnU" +
                "/vI5FKsvsQa+/ryBrS5eJxtKmvM/jL8NbPx54buZo4EXWbVDJbTqPmbHPlk9weg9CR718vLOKleXLVilfsd/EfAiq4WeIy+o3JK/K7apdmravzPka3uDJod1G3JT" +
                "H6kV/RJ+xWc/sl/CL/sWLAf8AkFa/nXtYiukXzkEZIH5Gv6Kf2LP+TS/hF/2LFh/6IWu6o21G/Y/nWs5OMObt+rPaaKKKwOUK5bxzJ5drbn/bNdTXHfEWQJaWf++" +
                "f5VE9jaj/ABEcjLc4Br8kf24G3/tiae3/AFCE/wDQ7iv1aluMk81+Pn/BRa+ntP2kFmt5XgmXSItskbFWH76ccEVOHlyVYzfQ9dVVQnCq1flaf3GDqCblyOaxbS4" +
                "vNJv47uxuZrK6jOUmgco6n2I5rzDQJtR1CTzp9Su0jB7TNlj+deo6Gq3UaoSzHGMk5P619WsWqi5pRsj9Gy+dTOXelHl7Xe/3HTeF/ESax8c/BXi7xVdRj7Bdwi9" +
                "vjF80qKcK77Ryy5HIGcAdxX6XXx8OW2gprF3q2n22kSIHS/luUWBlPIIcnBHpzX5e6poj2kYcqTE3y8joa5nVZ53hiheeR4Id3lxs5Kpk5OB0GTzxXJUwtOsuema" +
                "xVXLK1SD0k3d+vc6XV/FuqRfHDxRrC6sbzT21O6S3kim3Rzwb2EW0dNu3aR9K7Gf4qR2tm808hAA+6OrH0HvXhz3ckedpAPrVS4mkmbLuXPvXE8tVSSc+h1YPPKm" +
                "VYeVHDtttt6vqzM1pETTrry12KzFto7ZbNf0IfsWf8ml/CL/sWLD/ANELX89uuSgWEqk/MQOPxFf0JfsWf8ml/CL/ALFiw/8ARK08YkqijHoj8kzK3tUl2PaaKKK" +
                "4DyBDXDfFF9ljYn/po38q7qvPPi/L5WnWB6Zlb+VRPY3ofxEedyXXDc1+V/7ZHgS5+KX7XuleGrS7hsri/wBLRRPcAlECvcOSQOeimv06kueDzX5vfH/VotI/bj8" +
                "OX88yQQw6YWaSRtqqP9JGST061lS1mkz2eSNWpCE9m0mfM3jfwdJ8NPF2qeGZbj7XJps3kmdUKCTgEMFJOM5zXqv7PXgo+PYNXnS4QS6cYy0H8RVt2G+mVxWb+1F" +
                "rXhXxV45g1zw7rVrqV1eW6x6hDbEtsljAVX3Y2kFNo4J+4fWuW+CfxRuPhL4+sdaRWmsWzb39svWa3b7wHuMBh7qK+j+Ol7p9NgcY8uxSlTd4xdtNdD3bx74dj0v" +
                "w/KZVAYOqrx1Of8M157oXw2tPFi6lJceKdK8PR2UQkZtWkKb8nGFx198c8jg5rofi38Uo/FjTX9lC1tpcCGS3icjdIT0ZscZORwOnvXztdX9xevuuJnmbPVzSoc0" +
                "dLn2ufY+jTUVOnzTkr77I9AXwl4YGpfZ5/iDo6RbsGaG0vZF+o/cgH865D4haPceEL3FneWWtaRIf3GqWDlo5B6EH5o2/2WAP1HNZGxgM4O31pHXchDDcvQg8it5" +
                "qclaMrH5vXrTrQ5Y+6+6MO5vlubaQZIc9j9a/or/Ys/5NL+EX/YsWH/oha/ne1GygitZZEQK3HI+tf0Q/sW/8mmfCL/sWLD/0QteNXjKMrT3PmMSpxlae57TRRRX" +
                "OcYV5h8cpfI0rTTnrK38q9PryD9ou48jRtI5xumcf+OionsbUf4iPJXvuSM1+Wv7dtudU/aGhhV9nmaVGN2M4xLOf6V+lDX/J+avzS/bQvI4v2hLSeVwka6WmWPT" +
                "/AFk9ZUvj1PTkoycVLa6ueDN4amsJldLhX29iCM1c8vnirc+qWlx/q7mNv+BYqWK33ohAzkZ4r6TBQTbij26WHpJtUXp63NW5vnl8Dwx5OUnER+mCR/T8q5hlIjd" +
                "gMkKTj1rr9M04Xuh3lucBt4ZM+oH+RWFBbGOQqylSDgg1ty2lKKPaxdGpWVKUnvFL7tBPAPjuazkOlXrCW0lb5N4zsY9voa+jrH4G+EvHvhjT78ifRNSkhzI1oR5" +
                "bt03GMjAyMH5cda8Q0TwhpFzcJcTWoeQHdjcQufpXvfg7xGVthAzACPoOwFeLi3iKFP3XsfdcK5C6tCccz5Zx+z3Xz6fJng3x0+F8XwtjsIk1UaoL8SFcQ+UY9hX" +
                "r8xz9726V+7n7Fn/Jpfwi/wCxYsP/AEQtfhT+0lro8R63ZXETl7a3R7dCOQ3IJYfX+gr91f2Kzn9kr4Rf9izY/wDola5+apJJ1dz8f4lp0KOaVqWFVqcWkuvRfqe" +
                "1UUUUHy4hrxH9qKbydD0M+tw//oIr2+uB+MPw7k+Inh+K1tp0gvLd/NiMmdrcYIOOnb8vepkro0ptRmmz5Ba8JHWvzZ/beff8Z1b/AKhkf/o2Wv1wP7M/i7PElkf" +
                "fzq+Y/wBo7/gl38Qvi54htdZ0PUdFtLxIfIkW+uHCMm4sOVRiCCzduc9sc4wTTuzrrTjKFkz8rLK3e8u4oYxl3YKAK9Xi0wQRpGBwiha+obL/AII6/HTT51mg13w" +
                "ckq9G+2TZH/kCtL/h0z+0MTj/AISbwj9ftkv/AMj17+AxVHCpuabb7Hp5XjcPgoydWLcn2tsfHcfiSPSfEP2F22wsFViegY85/Wt68tYbpt7fLJ/eHf619JXP/BH" +
                "P46Xdy80uueEHlY7ixvp+T/34rR/4dKftCAAf8JL4RA/6/Jf/AIxWEsVzVJTPSw+fqPPGtFuN7ryR8h3fikeGJ9skEkw/hZCADXSaJ4wuNc0rz0jNrG5K7VbJYdO" +
                "TX0def8Egfj1fgifxB4Qlz63k3/xip7L/AIJG/tAWECw2/iPwjFEvRReTYH/kCk8QpTvLVHRR4oxFKbgpv2TWytf7/wDgnx/8SCDpEBPXzf6Gv3x/Yo/5NJ+Ef/Y" +
                "s2P8A6JWvzXt/+CP/AMbtcv7WDXPFnhS30/eDLNFPNK6L3Kp5K7jjtkfUV+tXws8BWfws+HPhrwfp0kk1joWnW+nQyyj55FijCBmx3O3J+tc1WaqT5kfLZjiY4uu" +
                "6sFZaHVUUUVieYFJjJoooACOKTHSiigQuKMUUUDFpMUUUAGKKKKAEYcU6iigAooooA//Z"
        val mouse= "/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAYAAAAAAAAAD/2wBDA" +
                "AMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQUF" +
                "BQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABgAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/" +
                "8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZW" +
                "mNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAH" +
                "wEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8" +
                "RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0" +
                "tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD3746ftx+IvDOu67ofhXw1DbDS719Pm1fUGMoMqkj5I1wB90kbifpXzL4h+KHjzxl4vsz4r" +
                "8RX2oHBnjtxNtgUhWwVjTCA5HpnirXx2i1JviT4+hVJGsH8RXU+zzEVXYOykgH5mP0PbpXnEOqXd5rFpJbQoJ48JGi5OVAxgliTgKMcngV6eEcKeJoyirq6vpdvX" +
                "ZLqeNmEatXBYmEmleMkne1tNG30/Q9K8P8Axx8YyCWzsPFWs6TcJzAFvnKHHYqTj8MfyrsLf9r74s+GLLThJr8N5K+8SjULSJgcHjJCg/r3rxq58LalcXj3gmgim" +
                "Z/MVVc5U+mcdqbq+uS291YfaLdkvLfdvUEAHIwCrc/XpX1uNp/7NVnjqNqm0ZWtzLmv02la/Zteh+f5bWjLHYejleIUqTd5w5m3B8jWl9XBtryTS7n0jpf/AAUR8" +
                "baWyLq3h3QdVX+9ZvLAWH1LOP0rtdK/4KY6CLi1h1jwTqVo07Fd1jdx3AUhSejCP0r4q1nWbfWI1Y20kNwnRzIGB6cHj2PT1rl9SbbqOlZ7zsP/ACE/+FfDSjFv3" +
                "dD9Up8yilJ3Z+1nw88dWHxL8G6X4l0uOeKw1GIyxJcqFkADFTkAkdQe9dJXkH7I/wDybp4I/wCvN/8A0a9ev1gahRRRQAUUUUAFFFFAH5W/H2FZvHvjBSZGlGs3p" +
                "VY3OcfaHzhQeefrjNeS+FWC69aEkJkuAT6lSBXrfx3t49Q+IPjCGQXEUaa3es0iQysrfv3wCQpU849cV49pmmm+1SOzMpiLFsSBTxgE8A4PavUwUpQxFKUFdqSsu" +
                "+p5OaRhPA141ZcsXGV3vZWd3byNG50rXY7piDPI24kPHJx/Pin+KlmSw0v7XhrzDB8HJxx1/wA+tXdAudQu7SSa51EQWiHZukVST+LfhWL4ks57S9WSac3STLmOU" +
                "45H4fUdK+rxVOFLATrUVNqpb4mmlrvZa62smfAZfVqV82pYbESpKVG9uSMk5Plta7VlZO7imzJd/LU8E/QVw/ijXbtdT08QssLRSyFUzktiI4yMe5HbrXRNrIHiy" +
                "3sRJhVt2lZPXJI/TH61xXijw3J4h1u81KGRFj0uETurE5ZQ4GBj/er4Ocj9VSP2h/YxvjqX7MfgC6KhDLYsxUHOD50le114r+xlbm1/Zk8BRH+Czcf+RpK9qqACi" +
                "iigAooooAKKKKAPys+Pcbn4geM2jZkddYvWXkFMi4fk5Bxxx2ryTw7dJDrkFxO4VMuWc9MlSP516n8f5GuviX4rhguoIpotbvd0crqpIM79Nx6fl1ry3T9Ka51uG" +
                "0uCNshJJRgQwAJ4I47V6mBc1iaTpq8uZWvte+h5WZqlLA11WbUOWV2t7Wd7eZra1DaweHBBaXaTKk4fhgSc54wPc1Q1eaL+xdJhWRZXVWZiDnGT0/p+FXtL+wWGm" +
                "XtxLbpeRxXGxGZFLEcAdaz/ABPFbCC01C3UQQTxksuAApHX/PtX1eO5pYaVePKm4RTir6RUrX7bpI/PsqlGnjaeEnztRqSam1H3pOF+V21Ts7/geI6hdS2Xxbvyz" +
                "OVltVZCchQAqjgdOuefrXPa54ln07V2iViIr1fJfOefnz2I7Dvnp0ror2WLW9Wk1qFmMImltUZzyRshZfwyH/Wuc1l5rSS58t4Y1ktn3GVSxIyB8p7H5uvSvgWj9" +
                "VTP3T/Y8cSfs1+BWXO02b4z/wBdpK9lrw79ii5Sf9lr4eyI29GsGII/67SV0njn9pf4X/DiSSLX/G2k2lzHnfbRzefMp9Cke5h+IpDPTaK+WtR/4KR/BazmMcGpa" +
                "rfgfx2+nsF/8fKn9Ku6J/wUQ+C+ryrHLrGoaYWON15p8m3803UAfTFFcv4G+J/hT4l2DXnhbX7HXLdMBzZzB2jJ6B16qfYgV1FABRRRQB+WXx6upYfG/jILMnlrr" +
                "V8xglUlZD578cEY9cV5PoV+t34gs2eKK3++n7pdoOVIH869Q+PYuJviF4w+yxCZk1q+WZEDFmQzP/d7dc//AK68XMn7zegERzuUJnA+mTmvQwtX2FaFVq/K0/uZw" +
                "Y3D/W8LVw97c8Wr9rqxp3Wl6naPLYLHK8DvkBFyrc8HOPpVf4goLLwumnghp47WTft7Fxj+pq/H4y1FIfLPlsQMCQr83+Fcl4pvd2n3UlxPmaUYBc8u3pXtYrEYK" +
                "nRnHCuUnNW977Mb3su+p8vgcFmdbE054+MIRptv3dXOTXLzPtp6u/keaJqNvFHf6HBGUu3K3ELhCY02ggs2PQEc+h/Pmrfw4Pth1HWblr1kJMcZ+SMDPGRnp7dPr" +
                "XSW2lvd6vNLaRPPeMjbtmSFQAE/yrI8VNa6cH824iu5sfu4GBMbnsSo5z/stj15r5W99z7dR7HZeKvjx8QNf+Hmj+EF8X6longvSY/KSz02X7PEIsljvCbWlySfv" +
                "HvxXmt7qPnMhsb25uxgEySELGAB24yxzj6eprE07Tp7uAi+kc2zOJBbNxk4/i56ZPH6+lbToAgVQFUDAAHAoA1fCwe5gkF7qEUN4WJjRkIj2jsW55+oFdT4Y8Rxe" +
                "HvEAW/RCrRuArwLcJIpGNy5YBgO5Vsj2riNNspLh3P3II/9bMeFTOep+gP5GvW/D3gFLjw7JPeRBLhik8KSttaDacoQD/H3PBxkKRgMDtSlOE1OCu1qcuJjTqUnT" +
                "qOyloWNC1++0zUbSbwdqd5p+owRKwvYnaBvNBOGXDErwcHn19SK/Qb9jH9uw/Eu5s/BXxAnhg8SOBHY6rwiXzdPLkHQS+hGA3TAON35YeMPFcc/ia4sPCR3u4zPJ" +
                "EpSG2b+PGSeBz3IHTJAzTdA1efSzauk7R3EO1hIrEMrA5yCOcg1NSftJOVrehpRp+ygoczdur3P6LFORmivl79l39sXw54/+D+l3fi/X7HTPElkxsb0XcoRrhkCk" +
                "TAf7aspPbduA6UVmbHyl8ZIUuPil4ykWJHKazfEhZWWRj5zY4zt429uT3HSvHtceR9QYyeYMAj96CCBk+oHtX6X/Fb9jHwT8SNQvdXtzc+H9bumaSW6sX+WVzyS6" +
                "Hg5PXGM18xeOf2DfiVptxHHpN7Z+JbJW+QmXyJEHHJVjjsOjH6VqpLqS12PlbVb5dIsXuplYKGCKuOWY9FHua8w8SeIS3mXF1KFYDt0Qf3R/nk13fx20bXfAfj7V" +
                "PDniRkjvtI2I8cbhkBeNJScjqcOP85z4VbahbeIvE8UVzPGlnGxdInbBncEYHPXnt6Z9aKji37mxnS9py3qWv5HpnhTx/beEPAOtX09skmo3JMcFrMuJMnAiYAjs" +
                "cvkc9uOteXW32m4826umLXc7FmY8kA9vr3Nbeulr28DMMhCSPY1Vit2k+SNC7noqjJrI3vpYqMhAyWwB3pguhPOkIcR7jje386v3CnSk86WMyP1VR2/z6/lXCy3s" +
                "aTzTRYinDblWOXfGmf5fgfwFAj6Nt/GGkeG/A7aShhtdEjkWa/nkALXc4IZI/VsYDED0QdC1edeMviVr3xEtbmGwZtF0GGNj8zlXnO3oTnjcQOB+NeYaVfyXN7Gb" +
                "9m1K3gLFI5JTtBYknA9zz71vs9xq0oaXCRg/LGgwo/CqTaTS6kOEZNNrYt6ZcrFYxWtrALeJVHmbTkyv3JPpkcDoPrzWlEXznJyOcYzVe2tREAAMV23ww0qHV/iN" +
                "4TsLjBhvNYs7VwTjIedFP8AOpLP27+BX7PXg/4afCfw1oa+GNL+2xWML380tqkkk90Y182RmYEklgfYAADAAFFeuoAqADoKKAHUUVR1XUJNPtmeK3kupcfLFGPvH" +
                "0z2oA/Pb9vP9iD4hfG34tTeIPANlYzWuo2cRvJ7y9S3CTpiLb/eOUCtkDorDrgH89fFXwok+CfiqLRpNW0Hx54lu8RqvhW8kvEgJPESZiUM5/2N3p9f2r8YfC74g" +
                "/GeWSz8Q+KJfBnhCX5ZdJ8OvtvLpD1WW4P3QehC5yD2ra+D37KHwr+BNxNd+D/CVpY6nMSX1O4LXN3g/wAImkLMq/7IIHHOTQB+C/i261fwprc+k674evdD1SHHm" +
                "WeoxvBMmem5GUEZpulePZNO02eO3021kvJDxNKW2j0yB978wPY9a/ff43fs1fDr9ojSIbDx14cg1U27bre8QmG6gPcJKmGAPdc4PccCvmTXv+CQfwhvpGbSdc8Va" +
                "Lk5CLdQzoP++4t360AfjL4j1LV9RuWbUpmbcchU+WP2wB/Wsy1uPshOIIpkJ+ZZF6/j1H4EV+vGrf8ABGjQL1WS3+JWoRxt0FxpMcjD6kSL/Ku9+Df/AASR+D/w9" +
                "mjvPFLX/wAQL9SGCai32e0Ug8fuYzlvo7MD6UAfmd8Nv2dPib8S/Av/AAkfhz4W6xeeHZS3ly2FuWiuCDyY95MjqCCMjdyOvFcLrOlal4M1f+yNZ0fUdP1UE5s7i" +
                "0kjlX22uATiv6QNF0Ww8OaTZ6XpVnBp2m2cSwW9paxiOKGNRhURQAFUAYAHFM1Tw9pmthRqOn2t+EOVF1CsgU+24HFAH4JfCP8AZt+KPxxvEi8HeDb+5ti219Tvl" +
                "+zWcPrulfAJHdVy3oDX6L/sxf8ABMPw/wDC3WtL8XeP9X/4S7xTYSpc2llboY9Os5lIZXAPzSspAILbR/s5wa+4oLeO1iWOJFjjUYVFGAB6AVJQAijAxRS0UAf/2" +
                "Q=="
        val monitor ="/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAUAAAAAAAAAD/2wB" +
                "DAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQ" +
                "UFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABQAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQo" +
                "L/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1h" +
                "ZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8Q" +
                "AHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOE" +
                "l8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMn" +
                "K0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U657xD8QfDXhG4gg13xDpOiT3CloY9Rvo4GkA6lQ5BOPauhr88P+Cgnhyy8U/tP/AAp" +
                "03UVDWV1o88Mp2g7Ua7hUsMggYBPJGKqK5pJEydlc+7LH4jeFtTYLZ+JNHuyegg1CJz+jVuW97DdKGhlSVT/FGwYfpX5Ow/s16JBoOrm88Pi5vrSS82PPHHFHBDC" +
                "itG8u23fcZQX2kmJSYyAxJAqx4d/Zg8PX9lHevoVvapJamaOdLOMI0qw3EjIrADODCo46ZNdHsF/MQ6nkfrDRX5h6V8AINEub9xMEtYYJZEe41G7tEQI8gJbY6kM" +
                "VgmIAzwMkennOpfDf4mx3pn8M/EjxTpWlTS7bJL7WbuCeZvNZDGI4ppMEFCPmKt1+U4NZTgoO1zWN5q6R+wNLX45xeO/ix8N75dP8R/FPxO+pTL5tpatr13Ms0eS" +
                "AzEyYjHBxnBODkCqV1+078a9K1q8t7Px3rixW8PnGe7uFkhQHkYL53rjPOT09eKym4QV5SX3l+zq25uR279D9maK/E6L/AIKDftHaBr0WlXHiqC4lkZRGX062cSA" +
                "9CGKHIr6F+G37a/xf8RRzSXl7aXFsjbUkNnErSep4UCt40Zz2Rg6kYq7P0sor4p0r9rHx350K3Njb3CO6ozfKoGTjOAv9a+y9JuGvNKs53ILywpI20YGSoJxSqUp" +
                "0rc6HGcZ/CW6KKKxLCvz1/wCChDyw/tEfD2WOzlvVXw3feZHDjeF+0RZYA4z9K/Qqvz8/b81NdK/aM+Hcz28lyh8N3ymOIqGx9oh5+YgH6Zreh/FiZ1PhZ594SHh" +
                "3W4BIXR4kOH3phoj6MpGVP1Ar0HRNJ8N388tvYalpmo3aJl4opEa4Rf8AaTrj3PFeaae/h/xFdRSxq1vqIGF3N5M4+m45I9jlfY1xN9o1pY/tKadJeeVDaLo/mPL" +
                "ApRnIMmGIQnLHA+7jPAAHSvqJyUEnZHkpczPo3UfBsU0E8Qi3QXCGKWMDAKkYP4YJ4rx6z+Dd9pS37p4i1bUr2GANCDI/kxpEylE2E4yuCykZwWOeTmveV8ReHtf" +
                "sY7/R7z7XYyR5Q2dyQhJ542kDv0PrV/4NXVjqXiTxDo97MSuow7bWOVhuicDnbnkFgcfhXyWc1JUlGS01Pu8hoUp0armuayv+Kv8ActT4P+J/2vR/HNjr41CTU5J" +
                "bdJblrxvMEUm9g0fOcAkZx6saNb1jwj46hsj4kuz4ctFLlmt5CYkUGPcSNrEjcznbwBzXV/tffD+Lwl4rgwssFtFL52yXJUg/QduD0714Lp3hvWfGFzeRWUYvd8g" +
                "jiUEKJV7KpJAyRk4OD174FfP4SvCrX9pNbdz6rN6MqWAWHoaqVrW/M9BtPC+i313Dp3h2eTXo43j+yXs6gGB5BgoBgYAAbueme9fTPwv+H9xp0dppoEZWPGXKZJP" +
                "fnPNcP+xF8NLm+0rxHc6ppl1bHS7428C3EJRjK6guCeQdqqvTs9fXXhX4ehr5ZGSZZCQflONo9Sa/QcO4uKn3PyCspU3yS3RPd/Cv7H4ZkulELTRoZAvl4JwM+tf" +
                "TPhh/M8N6U2c7rSI5H+4K+Jf21Ncl0P4T+RpuvXa3aaharcxw3BQlCGG1gpztO3OD3Ga+0/BT+Z4O0Jv71hAf/Ia1wY6o5yVzbCK0WzaoooryzuCvy7/4K1QPcfF" +
                "34bCK/fTpBo14Vmjfac+fHx1H+RX6iV+Yv/BVzTU1L4w/DaN9uBot43zgEDE8XXP1rSn8aIlsfDV/4t8Z6TbCJPEP2qNmwpkjRvoTuU/zra8A/EHxFDr8l7qUj6p" +
                "cx2rql255iGQVC8YAU84xjmsjxNoum2lxEiRpKvVnXaOef4cH+deg/CD7Cuk3trBYx2qQ6dcLcXO/P2h2YFdwJ9BjgDpzk8111JyhsZxSe5F4e+LPj60tLmbUfEC" +
                "usVsfIaCFEFuTKoLuqoNy8ng9AxI54PT/AAi+OPizUPF1jqc+pNqAS9EBKwxruQsOTgA8bl59q4Hw3pto2kxu1tEZ1tjtbaAw3KTwfYnOP8a6fwzYDSPCuojTrVJ" +
                "iXju5E2YlVSSDtPGVwPmA5HHbJrys1w86lJp6p/1c+v4fxMIV0lo/zXb5n298b/A19+0b8Mor3SYoke1uHMsN0ApZgMMAwHHIBA4z5mO1fA+s67b+DdauGs7GXTr" +
                "CVTDE00u543WPBcHH3y/zZ4AJA6Cvu/8AY88TrqMGoaZPdsYNTtTMqyTb1DooQhc8qcEAj/aHNeF/F34Z6PY6/d2niLRGv7WK5b7MiNKhYNnafk65yDg8D27/AAs" +
                "cY8LUjza30k/Nf5n3M8JHE0q+HhK3IlKC7p9L+Rj/AAC/aT1Pwlo+p6tplvJrT6i39nDTLiUKouwC9vdFj2MfnLIOp8uPGMCoPGn7R3xqeKW3t/GNtpiGMPP9mgh" +
                "z83HyHbkgEgZHIyK+ePFOtDQ/GkVnYRR6VpkdyrLpyDCx8bcuO5IJ6+p9a6XxDpnla4HTypzKsUbM8YQRAKrEoFwB3GCPTrX6Zg5udDTyt6H49jaSp12panVX3xN" +
                "8ZeJ/hH4st9c1JPEDWmp6f5aw26x+SWS5Lu2wDJOwctzX7T+AG3eBPDh9dNtj/wCQlr8VNO1fzPBvju0lUTNdR21yJgcMHjaQDdx82RK3XnIBz2r9qPhwc/D3wwf" +
                "XS7X/ANFLU4htz1MaXwnRUUUVymwV+Zn/AAVQCt8Z/hsHUOp0O+yp7/v4q/TOvBfjz+yboHx78X6Jr2tXMZbSrKayjtp4HdSJJEfeCksZBGzGDkHPtR7R03zJXJk" +
                "rqx+N6aVBrfjDRNKkRLWC8uY4pJtwGxC+GOT7c/hXZfCrUrGA+KRp6lrW6sJlkhkk2xZXyipxyTtYkgZHI5JBIP3lqH/BKHwHq2ozXlx4p1e28xiyw2EUcaRg/wA" +
                "K795A/Grdh/wSZ+E1pzJr/iuTPVUubVA31/0c1vKt7S0rWIhFpWZ+b+k6pZJtBurZR2JZf8a9D8ESDVdcnSLUrCIxW0citcTpEpAYgqCTzw5O0c5FfoBY/wDBL74" +
                "K2YG6LXrnH/PXUAP/AEFBXzt+3n+zR8Nv2dfhdouo+FNPurXVLvUHjea5vHmJhWCRiADx97y+cfzrd4pSVnEdOMqU1OL1R5z4L8ZaJoPim1utP1+yW3uIxPcWrPs" +
                "iiYMWZd4OM5RQCOuQa96/aT+IHw6+Ing+w1nR/HmkWOrGMi5sYr6L7RlgBnaSDw2T/wACJ7VJ/wAE7v2efBHxU/Z9bxL4t0P+17zUdSmRWlupo0aGMKEBRHVThi/" +
                "JGeTX1La/se/Bi0x5fw80Vsf89Ymk/wDQia+YrZXQqynLbmPr1xDiE6UuVXh+Ke6Z+FXjnTXPii/aGR7+N23rcR5fcCAQSfXnn3BFdVZa9b6ukWo61LJ9thVFSLb" +
                "gfKFUewPGcnPp9P3Btf2ZPhNZhfL+G/hU46GTSIH/APQlNbdn8GfAWngfZfBHhu2x/wA8dIt1/kle3Qk6EVFanzWKqLE1HUta5+G9p4ws00vXYv35a8t/KjG3OSD" +
                "nsf8AOa/dj4btu+Hnhc+ul2p/8hLVyy8IaHpwAttG0+3A6eVaxrj8hWsAAMAYA7VU5uo+ZnPGKjsLRRRWZQUlLRQAyRxGuTjA5ya8D8S/twfCbw88sUGtz69cxkg" +
                "w6TZySg49HICf+PV73NCJ0KMAyEEMCMgiuJ8O/A7wB4TIOk+DtFs3B3CVbJGcH2Ygn9aQ1bqfM+vft4eLNfZ4Ph18IdW1uRuEnv5TwexMcKtn6bxXhfxj+E37U/7" +
                "YSadB4n8M2Gg6PaNI8NmkaWapvwG3mV2kJwoHHvxX6hxQpBGqRoqIowFUYAp2KYXPzU8CfsD/ALR3gnRLWx8PfF+28J6fHkrpdlqt55MZJySFWPaCT1x1r0TS/wB" +
                "mL9qvTtp/4XvDMR3kuZn4/wCBwGvueigLs+QtN+DP7VNgR5nxm0q4UdpLeNs/nZ10tr4D/aZt1Af4heGrkjvJbgZ/K0FfTGB6UYoEeN/DnSvjXpPiND401fwvrWg" +
                "upVxY+ZHcxtjhlxEqtzwQccHrxz7Gn3B9KXFAGBxxQAtFFFAH/9k="
        val teclado = "/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAO" +
                "gCQABAAAASwAAAAAAAAD/2wBDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAU" +
                "EBQkFBQkUDQsNFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABLAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQE" +
                "AAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY" +
                "3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+T" +
                "l5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEK" +
                "RobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKm" +
                "qsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6KKTNAC0UmaMigBaKTNBPBoAKWuM0nx4NT+KfiPwf5" +
                "YVtI0vT9RMvdhcyXSAH6fZT+ddlmgBaKTNGaAFopM0ZoAWiiigDD8V66fD+j3N4CP3SlsN06V+fX7Qv7cnxD8MSabHoN5Y6f5rzzOcIv7qNTgEsrZ3blPAHTjrX1" +
                "/+0T4iXSfBl1GrBXkXYOfWvyH+Mmqx+IPiSjSPamCxjVI1vNNluAuTu+8pAxyOB6V1qny0ud9dj6P2FGhlsK043lOen+GK17btr7j3PVP+CgnxhTVLK4j1fS4o0t" +
                "Y4Z7fy4xG1wuBMxGwsBufaDnGRnmtTSP2x/jPqVvbWZ8d6Jb3kML3UkksKt5qiPoWWHHVGbAA+tfLGiaXYjxQ1tfm2Nrq90JRK+lTq103n8lFJ+RQ5xznAGT96vT" +
                "I7ez8N+CLy8vL37LqNrJbiykTTZcvYuJbdRN1yvlCUEKV+8oPofmcdXqwfJTk1fyXXTs9t35GNenh1SUowSfz9e/nb1TPbG/bR+K9rZX94fG2i3EOqLcx6eotNrW" +
                "7gIyHBj+UKrnk7u31PPX37cPxs024lWTxTpEg0aV0ugsKkTnzI0Gf3WSAxPAwcNgnOK5rUr7wzqnjE6ZJqDjTtY0j7XHENPk3RSSQrv8AJ+XA/dwRIcgnPfjbXIL" +
                "r2j3mj6Rr0t7DdXMl80Oqp/Z0oiuVMguHL9y24RjOT8yE4J4Hm0cbi5JNt626Lrf+6trOPzTPjsbUnT/hy/r8Tr9P/au+MFr4y1LWIdf0yPxFrdvapc3gRDi0t5L" +
                "lo4QPK2gK08gzjJyOeK6m4/bz+ME8ug6lb+I9KS0mjSBrc2yhZJhyzN+7JxjuCPXFeOzNpNnJ4k0uLUXF7o6pd6bcrYyCZwokZkBH8BklJwB0B5OSRoafJoWoHSL" +
                "iGeGTQ9Qs2j1ErYyeVZSCFbYSxqejFm3c5zwPevewlepUfv8A9aX/AOB5WPnp4vFR153+HqunXb5eZ30/7b/xvtLDUYJPGumtJpMwkubj7KuZRgKY1Pk4xlgenUd" +
                "e1ZD/APBR/wCM2ma/ex3uuWQt7qPzLcraxMkQOMBVMYPJB5JOOlctqkcL6FqcDsrautzJHDHPYyOdQtnljQvMR9/BhDcY5Oei8+MeJtMtdX1i7t4Iba6tmle50+e" +
                "306Z2ijdyQjDjK43cepzX0ihBx2R2YDE1asvfm2vl/kv603R9U3f/AAUU+MiS2sL65pVs88oECGygLOOfvkZUDGM4PUivsr9kT9pnXvjPouzxE1qdRhdkkkt4vLD" +
                "kMcHAOBxivyD1H+z7rwrHJbPp8gtbmN5RZaa8Z2nchJZsqxJZffg19ifsFeMBoPja6sWk/dyGOZAIzGCGG04U8gDC+x6jrT+rKUdFqz9IyrDUq7dOau5xlbyktV+" +
                "TXzP1cRsqM0VX0+cXFpHIDncB0orymraHgyTi2mfF37Y/xHaLWP7FhfOxH3YPIOMD9SK/LfU/GL65q+ppZnxNa6vJcPJ5i3ZWGKJMs+1AuclVwB7iv0a/b8ttG8B" +
                "+ItKuIZZJdX1fzrm43HiOIMoQD6nf/wB818YNr+gC6a4fS7PzyTmTyl3HPvivXxVSnVhThS0UVr69TpljpzjGnUV1HZHmsXiybXtNmuLY+K5JNOjSWeeW9eSZhvC" +
                "pFFwdgzIzt/uCukf4m6vYXNn4mhs/FMluluukR2Mt9LuLJDtadztOeJBjtuBPtXRprXhlUdV0y0RXGGAjXDD3prap4VeHyjplps3btnlLjPr0ryZYaM/is7+RzVM" +
                "Q52VtF/XZHnUfxS1ddHh06PSPFQjguBcRzLqU3mIQu0KDs4UAk49STTn+M14L25k/sLWo2uIDbvB/aUu0KYfJyF29QvIPrzXemfwif+YVZf8AflP8KZ5nhEH/AJB" +
                "tmCf+mSj+lU8PF7pfd/XY45xU90ecTfEyeSxs7V9H8QBraR5I5/7UkEvziMbS2zlQIxgDpk+tbI+IF/d6f4j1iOy1uytr+5igvNKju5WR1ldpi8Y2jbtaBV+j4rs" +
                "5B4MSKVvs+nl0IAijiBLe+cYxVf7Z4XEbRrYW2xjllEa4P6VapJdvuMZUYzVmvw87mafENxN4m8O2S3PiMCOzimsL43T74N+ZzFKdvIDFk4HTFcM+vXcOnf23FP4" +
                "l0/F0sN1bx3bIzlw77k+XgALjk9x616O174cd1ZbO3DKMK3lrkCqzv4d2FPsluEY5KiNcGuhO2lyKeHVN6f11PPItbs01HXNKhbWFi1O1+0W1ubs7UbyxOu9QPmY" +
                "MMZHfvXqX7O/xKl0fxvpV4BfwzLGbWZr992cfMuzgYX5AMdsn1rFurnQlO9bWAuowG8tcgelcXrfiGPRmjuNMVLd4JVmVY/kDFWB5x9K7KWJ5KsakldJ3/wAz2sL" +
                "ip4ZxlDeOx+/fwl8Tp4o8DWF+jbg64OPXA/xoqv8ABTQNI0r4baLLoM0k+kahbxX9q0hyfLljVl/TFFeZiJ0nWk6e19DOU3OTk+p+Wn/BSL4qJrn7ResWEc++HRo" +
                "INPTB4+Vd7j8HkcfhXxteeKHZuHP4Gv0Y+Of/AASe8f8AxS+Lfi/xVp/xE0aGw1rVLjUIYL+3mMsSyyM/lkqMHbu2jHYCuCP/AARZ+JDf81H8Nf8AgNP/APE1Kmk" +
                "rGfKfDB8Qykn5z+dR/wDCRTf3z/31X3X/AMOWfiQP+aj+Gv8AwGn/APiaQf8ABFj4kf8ARRfDP/gNcf8AxNV7RC5T4U/4SCZujkfjQNcnP8bfnX3YP+CLXxJH/NR" +
                "vDH/gNcf/ABNOH/BFz4lD/mo3hn/wGuP/AImlzoOU+EU1eUHhio9AamGsSjq5/Ovun/hy/wDEr/oo3hj/AMBrj/4ml/4cw/Ev/oo3hj/wGuP/AImnzoOU+Fl1mQf" +
                "xn86U63Jn75/Ovuc/8EX/AIln/mo3hn/wGuP/AImkP/BF34lH/mo3hn/wHuP/AImlzoOU+Fn1xsEFj+dZ91eG4U5Oa++P+HLnxJ/6KL4YP/btcf8AxNH/AA5c+JI" +
                "5/wCFi+GP/Aa4/wDiaOdByn3D/wAE5fHf/CffsfeAppJN91pUEmjzDqV+zyNHGP8Av0Ij+NFWf2Jf2WNV/Za+D914S1bxDHrN9datNqTzWKskKB4oowihuekIJPq" +
                "xorFln0jRRRSAKKKKACiiigAooooAKKKKACiiigAooooA/9k="
        val camara = "/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAdgAAAAAAAAD/2wB" +
                "DAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFBQ" +
                "UFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCAB2AGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQo" +
                "L/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1h" +
                "ZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8Q" +
                "AHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOE" +
                "l8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMn" +
                "K0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6KKKACiimPIE60APpC2K8p+Nfx98OfBfw9Lquv6jHYwbvLjXG+WaTHCRoOWb6dBycD" +
                "mvhLxp/wVFvbq+kj0LQooIAx2y6nctI7D3ijIVfwkNAH6hBwTinV+XvhX/gpp4qE8b3Wk6DdwjrFGk9uze28ySY/75P0r6r+B/wC2/wCDfi5qVpod7HL4U8R3R22" +
                "1tdzLNbXT/wByKYAZb/ZZUY9gaAPpeiqlveh2MbjbIO3Y1boAKKKKACiiigAoopKABm2jNQsMgsfyp0jBmA7daZcki3kOeQpP6UAfj1/wUS8T3vib42WdgblzZ2O" +
                "lo0UOflR5JJC7AepCpz/sivke60poFJGeO9fTH7VAbWvj5fn00+2Uk9uXP9a8q1LR7eG2c4CgDlnoA8tivnspRtbkHsa7TR/F00dqxjmZJY8SRyI2GR1OVYHsQQC" +
                "DXmnjGJrTVi8IJhYDEi/dJ9M1HofiBt5t5WzvGFbP6UAf0UeD/Es2seCfB2sTy77jUdItbuST+8zxKxP5tXe2N2LmEHOT7V4h8NLsD4H/AApbOS3hmw5+ltHXpfh" +
                "fUsnaT1oA66ikBzS0AFFFFABSHpS0h6UAQjBmYeij+tMvc/ZJ/wDrm38qcp/0mT/dX+tOmj82NkPRgQfyoA/Fz47t5vxj1qQ/e+y2wz7YevDPFrf2p4lttMuJxbW" +
                "KoruSCQSxPJHfAHT3r6L/AG4/AWteDddj8R2IeO3QnT78qudjKzeUx9iWZc+u0d6+Rb3VLm/uBPNM0kqDaGIGcemOP5imgNfXvDtrbm4e1AuNO3BA5A+cEEhSAOv" +
                "yn8RkV5BewPYarPChLCGZlDD2OK9FuvE8kEKQ+ZJMY8tGjfcQnvjJyasfCL4Tan8afiPonhPR7Z5bvUrlUklUZEEWcySt7KuWP0x1Iptp7Aftr8Mpj/wof4UAghh" +
                "4bshg/wDXCOvQvDdwRMvPpWJqul23h3TtD0OzTy7bTLOO3iQfwooCqPyUVq+HP9ev1FSB6jA26NT6gGpKhtf+PeP/AHR/KpqACiiigApD0paKAKyf8fUv+6v9amN" +
                "Qxn/S5f8AdX+tTUAeF/Hv4OW/i21vbz7El9aXUZjvrV03BhjBbHcEdfz+n5vfEb9gO9+3y3Hg3VoUgc5FjqpcCP2WVQxI9Mr9Sa/ZNwNprhfEXgjR76ZpjaLDKer" +
                "RfLn8OlAH5B+Fv+CdfjzXNUjGpahoumWpIEkiTyzuB6hBGoP4sK/QH9mj9mrwb+zPoU9xp8bX+tXShbrVLlR50uOdiAcImedo9BknjHqjeHrPS2/dITj+8c1nXzF" +
                "2yxzjp7UAMub2TUb2S4lJ3u3TsB2FdH4dOJ1+orlIjhxXVeHuZhQB6la/8e8f+6P5VNUNp/x7Rf7o/lU1ABRRRQAUhOBS0h6UAVk/4+5f9xf5tU9QoMXUn+4v8zU" +
                "1ACP901hat/FW6/3TWFq38VAHHap1NczedTXTap3rmbzqaAKkf3q6nw5/rh9a5aP71dR4eOJgaAPVbP8A49ov90fyqaobT/j2j/3R/KpqACiiigApD0paQ9KAK6f" +
                "8fcg/2F/m1TVCn/H5L/uL/NqmoARuhrn9ZlWJXZyFUdSTgV0DfdNc14gthdxPESVzg5Hsc/0oA5PUJo5B8kiMDnG1gc+tc3eD5ia2bnT/ALPP5hkLnBHI9gP6Vj3" +
                "nU0AVI/vV02gnEgrmE+9XS6IfnoA9Zs/+PaL/AHR/IVNUFj/x6xf7i/yqegAooooAKSlpGGRQBzXi+SeMWRt7iS3YuQWiPXjv61lf2hrsC5j1BJiP+e8C/wDsuK1" +
                "te8NXupgfZ9QMTDld6A4Nc6/hzxfbcJdadeJ6SxFW/QigBbjxZ4ktwcx6dJj/AKZuv/s5rC1Hxxr/ADnTrF/pI4rWk0rxRg7tNtJT6pcFf5g1Rn0LxFIOdFjye4u" +
                "gf/ZaAOO1Hxlr75/4ltmM/wDTRv8ACueufEniKVji0sI8+pdv6ivQZ/B3iKbppKD63A/wqt/wrbxHMf8AjxgjJ9Z84/SgDg4dQ8RTN80lnEP+mcBz+rGu38Bpfy6" +
                "3ZfbLx5ozKoMYVVU898AZ/GrcHwp8RseWsYD6sS2P1rf0T4X6tZXMc1xqyfIchYY8UAemxjaMDpT6rWNs9rCqPIZSB95qs0AFFFFABRRRQAUmBRRQAYFGBRRQAYF" +
                "GBRRQAYFGKKKAFooooAKKKKAP/9k= "
        val consola = "/9j/4AAQSkZJRgABAQEAYABgAAD/4QBARXhpZgAASUkqAAgAAAABAGmHBAABAAAAGgAAAAAAAAACAAKgCQABAAAAZAAAAAOgCQABAAAAVwAAAAAAAAD/2w" +
                "BDAAMCAgMCAgMDAwMEAwMEBQgFBQQEBQoHBwYIDAoMDAsKCwsNDhIQDQ4RDgsLEBYQERMUFRUVDA8XGBYUGBIUFRT/2wBDAQMEBAUEBQkFBQkUDQsNFBQUFBQUFB" +
                "QUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBT/wAARCABXAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQ" +
                "oL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1" +
                "hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8" +
                "QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNO" +
                "El8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyM" +
                "nK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD9U6KKKACiiigAooooAKKKzNR1F4gyRHB6E00m3ZCbsT3mpw2ZwzbmPRV61kXXjnSNJ1" +
                "OzstV1Ky0ya+DC0jup1jadlxuVdxG4gMDgc9azpG5LEknrmvkz/gpgstp8JvDOsRlT9m1RrZldQyt5sTHBB6/6ut1SWxPMfZ2o+MdD0qORrrVrOExjcVMylsdvlB" +
                "yfwrXjcSIrA5DDI+lfh1+yR4lvNY+PejaYiQW8Fydrx20QQPmRBzjHrX7iIu1VA6AVE4clhp3H0UUVkUFFFFABRRRQAUUUUAFcncWKWuoX86u7PcyB2ViMLhQoA4" +
                "9FrrK5XVJdtxN7Mf51rT3ZEijO+M183f8ABRzTRqH7LGpz7c/Yr+0uc+nzFM/+RMfjX0RI5c5rwD9vnxT4et/2ZPFnh++1qwttbvraGSx06S4UXFx5c8bkpHncQF" +
                "RiSBgYrqjujOWx+en7AFl/an7VPhWMjIDMx+gwx/8AQRX7kr0r8Wv+CX2mf2j+1RYybSwt7GeYnHTjH9a/abpWVf7JcNhaKKK5TQKKKKACiiigAooooAK878Z+I9" +
                "M8L2d/qer38GnafbsTLc3LhEXnA5PckgAdSSAK9ErxbW/Adhd+M7jX9Umn1i7hmZtPhvCDBpw6ZhjAADnnMjZfkgMF+WtqSu2RI4281vxj8TQyaR9p8CeGG/5idz" +
                "AP7WvF/wCmMLgi2Uj+OUM/X92hw1cx8WvhH4c8K/s0fFf+xtLjTUrnQLuW51O4Zri9umSMtumuJC0kh+XPzMcdsCvZJZTkisL4mWP9q/BP4hWeMmfw9qMePrbSD+" +
                "tddrIk/P3/AIJHz3n/AAv7XbeGUpZvpDSzoFHzsrgJk9cDe3HQnBPQY/X2vyX/AOCPdmZvjF40uiARFoyKD6FpR/hX60VjiVZx9P1Y6ewUUUVyGgUUVXvrtLG1kn" +
                "kICoM/X0FAFiiuAj+Jr28pN5bw+W8ixoICxEeTgFmYDOSVAwB171of8J/Gf+WH61r7Kdr2J5kdfRXB3fxLJnktraBEmiRJWaYFkKsWG3jkH5evPXoa6zRdV/taxS" +
                "cqkch+8kblwD7EgZHvgVMoShugTTL7dK831aTc8jHuxNejyHahPoK8u1aT73NbUN2KRlySAt1qxc2R1Twjr9mMn7TYXEP/AH1Gw/rWc0nzV0vhWITl4yeHBU/jXX" +
                "LYk/Pv/gjXbeZ4v+It0f4bC1Qfi7n+lfqfX5i/8EabNkn+JUjgh1jsIzn/ALa1+nVc+K+NLyQqWzCiiiuM2EPSuJ+KOpG1023twcCZyW57AdP1H5V29eRfHfVodG" +
                "t4Lm5kEUEMEkruTwqryT+QrSn8aJex8X/tXftNH4W3ll4V0C4WLX7h4765uwy/6NGkiuiDII3OUztIICgjHzA18+3f/BQr4sR3EyW15pktsDlJW05d+PwOD+VfVf" +
                "7MX7I/hf8AaL8Nax8VfiVZXWoXnie+lm0+z+0NElvaK2yP7vJOFwMnG1Vr2Bv+CbfwMZy50C/3E541OYf1rolVSdiLHyd+zZ+2Ze+JPH40nxZex3E+vyK1pdqFVL" +
                "efy1RY9qgbVcKAAc/Nj+8cfob8L9VE91cW+R86+Zx3bP8A+uvnH43/APBOz4f2fwx1rUfAWn3uleLdOha+sZlu3kMjxjd5ZDZ5bbwRgg4PqD2v7JPxBPxE0bw7rJ" +
                "I869sy04XosqgrIPpvVqHLngxrRn01fP5dlO/91GP6V5Nq8+Ca9T1nedLuhGpdzGwCjqcivB9e8UWFvrL6VLe28WpKNxsnlUTAHodmc1NDqORbEuW6113g5/3w+t" +
                "cDFdjd1rsvBt0BMvOea6ZbEnx1/wAEhrP7Lc/F5cY8u/tosegHnV+jtfnr/wAEn0xffGs/3dbhQfgZq/QquXESUp3XZfkh01aIUUUVzGgVxHxc+FGlfGDwhf6Bql" +
                "xd2UV3byWxurF1SVEddrgbgRyOOR+VFFAG74L8Jaf4E8J6R4e0qMxadplpHaQKeuxFCjPvxW1RRQA10EilWGVPBB715n8HfgB4d+C66kNFkvJlvLy4u0junVkthK" +
                "+8xxAKMKD0zk9eaKKLtLQVj0i7tY722lt5QTFKpRgrFSQRg8jkfhXiXjH9jP4X+NWaTUtEN3MxyZr1hfSD2D3IkZR7AiiigZzenfsF+BNFk36Zq3iXTFByIrDWrq" +
                "2jH/AIpFX9K9F8OfASz8MpALTxX4mPlrh/PvEuPM5J5MsbkcccEcDucklFPnl3FZGl8PPgb4P+F0pk8PaYLOQszl93zMzABmYjG8nHVsmvQKKKQwooooA//9k="

        val query2 = """
            INSERT INTO productos (nombre, precio, descripcion, imagenBase64) VALUES
            ('Samsung Galaxy S24 Ultra', 15899.00, 
            'Smartphone con una pantalla de 6.8 pulgadas, cámara de 200 MP, y batería de 5000 mAh. Está disponible en diferentes colores.', '$smartphone'),
            ('Lenovo IdeaPad 5i (Intel Core i7)', 11501.00, 
            'Pantalla 15.6" FHD táctil opcional con bordes estrechos, tapa de privacidad en la cámara web y lector de huellas digitales opcional.', '$laptop'),
            ('Apple Watch Series 9', 6408.20, 
            'Reloj inteligente con sensores de frecuencia, temperatura y frecuencia cardiaca, y altímetro siempre activo, brújula.', '$smartwatch'),
            ('Audífonos inalámbricos Sony WH-1000XM5', 12141.20, 
            'Redefinen el audio sin distracciones y la claridad en las llamadas. Dos procesadores controlan los varios micrófonos para ofrecer un sistema de noise cancelling sin precedentes', '$audifonos'),
            ('Tablet iPad Air (5ª generación)', 14999.00, 
            'Capacidad de 64 GB. 256 GB, Modelos Wi-Fi. Grosor: 247.6 mm. Ancho: 178.5 mm. Profundidad: 6.1 mm. Peso: 461 g. Modelos Wi-Fi.', '$tablet'),
            ('Mouse Gamer Logitech G502 Hero RGB', 1329.00, 
            'G502 HERO cuenta con un sensor de juegos HERO 25K con seguimiento de precisión submicrométrica, LIGHTSYNC RGB personalizable', '$mouse'),
            ('Monitor LG UltraGear 27 144Hz', 6299.00, 
            'Con una velocidad IPS 1ms comparable a la de TN, que proporciona una imagen residual mínima y un tiempo de respuesta más rápido', '$monitor'),
            ('Teclado mecánico Razer BlackWidow V4', 3701.07, 
            'Retroiluminación RGB, teclas mecánicas y una prioridad de entrada avanzada,retroiluminación RGB, y teclas de ABS de doble inyección ', '$teclado'),
            ('Cámara de seguridad Xiaomi Mi 360°', 699.00, 
            'Se puede conectar a la tarjeta SD. Se recomienda utilizar productos de marca de clase 4 o superior, el formato debe ser FAT32 y el tamaño debe ser 16 ~ 32G', '$camara'),
            ('Consola PlayStation 5 Standard Edition', 10590.00, 
            'Procesador AMD Ryzen Zen 2 de 8 núcleos y 16 hilos, con frecuencia variable de hasta 3.5 GHz y tarjeta gráfica AMD Radeon RDNA 2 con 16 GB de memoria.', '$consola');
        """

        db?.execSQL(query1)
        db?.execSQL(query2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS productos")
        onCreate(db)
    }

    fun obtenerProductos(): List<Tec> {
        val productos = mutableListOf<Tec>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM productos", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val nombre = cursor.getString(1)
            val precio = cursor.getDouble(2)
            val descripcion = cursor.getString(3)
            val imagenBase64 = cursor.getString(4)
            productos.add(Tec(id, nombre, precio, descripcion, imagenBase64))
        }

        cursor.close()
        db.close()
        return productos
    }
}
