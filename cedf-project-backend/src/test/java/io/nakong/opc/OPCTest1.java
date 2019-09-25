package io.nakong.opc;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
import java.util.Map;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.Leaf;

public class OPCTest1
{
    public static void dumpItemState ( final Item item, final ItemState state )
    {
        System.out.println ( String.format ( "Item: %s, Value: %s, Timestamp: %tc, Quality: %d", item.getId (), state.getValue (), state.getTimestamp (), state.getQuality () ) );
    }

    public static void dumpTree ( final Branch branch, final int level )
    {
        final StringBuilder sb = new StringBuilder ();
        for ( int i = 0; i < level; i++ )
        {
            sb.append ( "  " );
        }
        final String indent = sb.toString ();

        for ( final Leaf leaf : branch.getLeaves () )
        {
            System.out.println ( indent + "Leaf: " + leaf.getName () + " [" + leaf.getItemId () + "]" );
        }
        for ( final Branch subBranch : branch.getBranches () )
        {
            System.out.println ( indent + "Branch: " + subBranch.getName () );
            dumpTree ( subBranch, level + 1 );
        }
    }

    @SuppressWarnings ( "unused" )
    public static void main ( String[] args ) throws Throwable
    {
        args = new String[5];
        args[0] = "192.168.2.116";
        args[1] = "";
        args[2] = "codyy";
        args[3] = "123456";
        args[4]= "B3AF0BF6-4C0C-4804-A122-6F3B160F4397";
        // create connection information
        final ConnectionInformation ci = new ConnectionInformation ();
        ci.setHost ( args[0] );
        ci.setDomain ( args[1] );
        ci.setUser ( args[2] );
        ci.setPassword ( args[3] );
        ci.setClsid ( args[4] );

        // create a new server
        final Server server = new Server ( ci, Executors.newSingleThreadScheduledExecutor () );
        try
        {
            // connect to server
            server.connect ();

            // browse
            dumpTree ( server.getTreeBrowser ().browse (), 0 );

            // add sync reader

            // Add a new group
            Group group = server.addGroup ( "test" );
            // group is initially active ... just for demonstration
            group.setActive ( true );

            // We already have our group ... just for demonstration
            group = server.findGroup ( "test" );

            // Add a new item to the group
            final Item item = group.addItem ( "Saw-toothed Waves.Int2" );
            // Items are initially active ... just for demonstration
            item.setActive ( true );

            // Add some more items ... including one that is already existing
            final Map<String, Item> items = group.addItems ( "Saw-toothed Waves.Int2", "Saw-toothed Waves.Int4" );

            // sync-read some values
            for ( int i = 0; i < 10; i++ )
            {
                Thread.sleep ( 100 );
                dumpItemState ( item, item.read ( false ) );
            }
        }
        catch ( final JIException e )
        {
            System.out.println ( String.format ( "%08X: %s", e.getErrorCode (), server.getErrorMessage ( e.getErrorCode () ) ) );
        }
    }
}
