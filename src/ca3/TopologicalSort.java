package ca3;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import jdsl.graph.api.Graph.*;
import jdsl.*;
import jdsl.graph.api.Graph;
import jdsl.graph.api.Vertex;
import jdsl.graph.ref.IncidenceListGraph;
/**
 *
 * @author Maziar
 */
public class TopologicalSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

		RandomAccessFile file = null;
		Integer changer = new Integer(0);
		int i, course_no, pre_need_no, term_course_no, pre_need, legal_course;

		Graph G = new IncidenceListGraph();

		String temp[];
		String buffer = new String();
		file = new RandomAccessFile("input.txt","r");

		buffer = file.readLine();
		temp = buffer.split(" ");
		course_no = Integer.parseInt(temp[0]);
		pre_need_no = Integer.parseInt(temp[1]);
		term_course_no = Integer.parseInt(temp[2]);

		Vertex[] v = new Vertex[course_no];

		for(i = 0; i < course_no; i++)
			v[i] = G.insertVertex(i);

		for(i = 0; i < pre_need_no; i++)
		{
			buffer = file.readLine();
			temp = buffer.split(" ");
			pre_need = Integer.parseInt(temp[0]);
			legal_course = Integer.parseInt(temp[1]);
			G.insertDirectedEdge(v[pre_need-1], v[legal_course-1], 0);
		}

        int counter  = 0;
        int answer = 0 ;
        int [] tempArray = new int[course_no ];
        boolean isPre = false;
        int startPoint  = 0;
        counter = 0 ;
        for ( int i1 = 0 ;  i1 < course_no ; i1++ )
        {
           for ( int j = startPoint ; j < i1 ; j++ )
           {
               if(G.areAdjacent(v[tempArray [j]] , v[tempArray[i1]]) )
               {
                    isPre = true;
               }

           }

            if ( counter <= term_course_no )
            {
                if ( isPre ||  counter == term_course_no )
                {
                    answer++;
                    startPoint = i ;
                    isPre = false;
                    counter = 0 ;
                }

            }
            counter++;
        }
		RandomAccessFile output = new RandomAccessFile("output.txt", "rw");

        boolean bool =  G.areAdjacent(v[2] , v[6] );
        if ( counter != 0 )// latest course if remain
		{
            System.out.print((answer+1)+"\n");
			output.writeBytes((answer+1)+"\n");
		}
		else
		{
            System.out.print(answer+"\n");
			output.writeBytes(answer+"\n");
		}

////ew/////////////////////////////////////////////////////////

       		file.close();
			output.close();

    }



}
