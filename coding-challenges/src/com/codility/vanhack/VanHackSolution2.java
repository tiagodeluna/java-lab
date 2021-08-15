package com.codility.vanhack;

public class VanHackSolution2 {

	public static void main(String[] args) {
		// Foi um teste de SQL, implementado em POSTGRESQL. A solucao
		//esta listada abaixo.
		
		/*
			SELECT recipient AS account_name FROM
			(
			    SELECT recipient, sum(amount) AS sum_amount FROM 
			    (
			        SELECT *, ROW_NUMBER() OVER (PARTITION BY 
			                 recipient ORDER BY amount DESC) AS row_id FROM transfers
			    ) AS max_amounts_per_account
			    WHERE row_id < 4 GROUP BY recipient ORDER BY recipient
			    ) AS sum_of_amounts
			WHERE sum_amount >= 1024
		 */

	}

}
