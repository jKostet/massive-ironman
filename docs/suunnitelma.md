


Idea: Kissa ja hiiri -tyyppiset robotit

Robotit toimivat rajatulla alueella, jolla on muutama näköeste (esimerkiksi pahvilaatikko), jonka taakse hiiri voi piiloutua.
Kissa käy aluetta läpi jonkinlaisen algoritmin avulla.

![Alt text](https://github.com/jKostet/massive-ironman/blob/1b07dc72ff16c6ea401d209bb8b5a411112a93f9/docs/pictures/suunnitelma_robot.jpg "Robottien rakenne")

Kissaan on kiinnitetty älypuhelin, jonka kamera lähettää smartcam-applikaation avulla kuvaa tietokoneelle.
Kissalla on myös ultraäänisensori etäisyyksien mittaamiseen, jotta lähestyvät seinät ja esteet voidaan havaita.

Hiiri on käytännössä liikkuva palikka, jonka sivuilla on reacTIVisionin tunnistamia kuvioita (fiducial).
Tunnistaessaan hiiren, kissa ryntää sitä kohti. Jos hiiri katoaa jonkin esteen taakse, kissa pyrkii ohittamaan esteen,
ja sitten löytämään hiiren uudestaan.

![Alt text](https://github.com/jKostet/massive-ironman/blob/1b07dc72ff16c6ea401d209bb8b5a411112a93f9/docs/pictures/suunnitelma_toiminta.jpg "Tavoiteltu toiminta")

Alueen yläpuolella on myös toinen älypuhelin kuvaamassa alaspäin (lintuperspektiivi).
Hiiri näkee tämän kuvanm, hahmottaa sen avulla sijaintiaan kissan suhteen ja pyrkii piiloutumaan näköesteen taakse.
Näköesteet merkataan hiirelle kuvaan, ja ne ovat staattisia.