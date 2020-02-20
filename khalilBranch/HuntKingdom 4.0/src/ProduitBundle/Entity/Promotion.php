<?php

namespace ProduitBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;


/**
 * Promotion
 *
 * @ORM\Table(name="promotion")
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\PromotionRepository")
 */
class Promotion
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */

    private $id;
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateFin", type="datetime", options={"default" :"CURRENT_TIMESTAMP"})
     */

    private $dateFin;
    /**
     * @var \DateTime
     *@Assert\GreaterThan("today")
     * @ORM\Column(name="dateDebut", type="datetime", options={"default" :"CURRENT_TIMESTAMP"})
     */


    private $dateDebut;
    /**
     * @var float
     * @Assert\GreaterThanOrEqual(0)
     * @ORM\Column(name="prix", type="float",options={"default" :0})
     */
    private $prix;
    /**
     * @var int
     * @Assert\GreaterThanOrEqual(0)
     * @ORM\Column(name="pourcentage", type="integer")
     */
    private $pourcentage;

    /**
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumn(name="idProduit", referencedColumnName="id")
     */
    private $idProduit;

    /**
     * @var int
     *
     * @ORM\Column(name="active", type="integer",options={"default" :0})
     */
    private $active;



    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set pourcentage
     *
     * @param integer $pourcentage
     *
     * @return Promotion
     */
    public function setPourcentage($pourcentage)
    {
        $this->pourcentage = $pourcentage;

        return $this;
    }

    /**
     * Get pourcentage
     *
     * @return integer
     */
    public function getPourcentage()
    {
        return $this->pourcentage;
    }

    /**
     * Set idProduit
     *
     * @param \ProduitBundle\Entity\Produit $idProduit
     *
     * @return Promotion
     */
    public function setIdProduit(\ProduitBundle\Entity\Produit $idProduit = null)
    {
        $this->idProduit = $idProduit;

        return $this;
    }

    /**
     * Get idProduit
     *
     * @return \ProduitBundle\Entity\Produit
     */
    public function getIdProduit()
    {
        return $this->idProduit;
    }

    /**
     * Set dateFin
     *
     * @param \DateTime $dateFin
     *
     * @return Promotion
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    /**
     * Get dateFin
     *
     * @return \DateTime
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    /**
     * Set dateDebut
     *
     * @param \DateTime $dateDebut
     *
     * @return Promotion
     */
    public function setDateDebut($dateDebut)
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    /**
     * Get dateDebut
     *
     * @return \DateTime
     */
    public function getDateDebut()
    {
        return $this->dateDebut;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return Promotion
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Set active
     *
     * @param integer $active
     *
     * @return Promotion
     */
    public function setActive($active)
    {
        $this->active = $active;

        return $this;
    }

    /**
     * Get active
     *
     * @return integer
     */
    public function getActive()
    {
        return $this->active;
    }
}
